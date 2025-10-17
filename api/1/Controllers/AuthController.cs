using Microsoft.AspNetCore.Mvc;
using _1.Models;
using Microsoft.AspNetCore.Identity.Data;
using Microsoft.AspNetCore.Identity;
using System.Security.Cryptography;
using Microsoft.AspNetCore.Cryptography.KeyDerivation;
using Microsoft.EntityFrameworkCore;
using System.Security.Claims;
using System.IdentityModel.Tokens.Jwt;
using Microsoft.IdentityModel.Tokens;
using System.Text;

namespace _1.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class AuthController : ControllerBase
    {
        private readonly ApplicationDbContext _dbContext;
        private readonly UserManager<AppUser> _userManager;
        private readonly SignInManager<AppUser> _signInManager;
        private readonly ILogger<AuthController> _logger;
        private readonly IConfiguration _configuration;

        public AuthController(ApplicationDbContext dbContext, UserManager<AppUser> userManager, SignInManager<AppUser> signInManager, ILogger<AuthController> logger, IConfiguration configuration)
        {
            _dbContext = dbContext;
            _userManager = userManager;
            _signInManager = signInManager;
            _logger = logger;
            _configuration = configuration;
        }

        [HttpPost("[action]")]
        public async Task<IActionResult> Login(AuthLogin data)
        {
            var user = await _userManager.FindByNameAsync(data.Username);
            if (user == null) return NotFound("User not found");

            bool isValidCredentials = await _userManager.CheckPasswordAsync(user, data.Password);
            if (!isValidCredentials) return Forbid("Invalid password");

            string accessToken = await CreateTokens(HttpContext.Response, user, data.RememberMe);
            await _dbContext.SaveChangesAsync();

            return Ok(new {token = accessToken});
        }

        [HttpPost("[action]")]
        public async Task<IActionResult> Register(AuthRegister data)
        {
            var user = new AppUser()
            {
                UserName = data.Username,
            };

            var result = await _userManager.CreateAsync(user, data.Password);
            if (!result.Succeeded)
            {
                return BadRequest(result.Errors);
            }
            await _userManager.AddToRoleAsync(user, "User");
            
            string accessToken = await CreateTokens(HttpContext.Response, user, true);
            await _dbContext.SaveChangesAsync();

            return Ok(new {token = accessToken});
        }

        [HttpPost("[action]")]
        public async Task<IActionResult> Refresh()
        {
            if (!Request.Cookies.TryGetValue("auth-refresh-token", out string? token) || token == null)
            {
                return BadRequest("Invalid credentials");
            }
            var tokenData = _dbContext.RefreshTokens.Include(x => x.User).SingleOrDefault(x => x.Token == token);
            if (tokenData == null)
            {
                return BadRequest("Invalid credentials");
            }

            if (tokenData.IsRevoked)
            {
                return BadRequest("Refresh token revoked");
            }
            if (tokenData.ExpiresOn < DateTime.Now)
            {
                return BadRequest("Refresh token expired");
            }


            string accessToken = await UseTokens(HttpContext.Response, tokenData);
            await _dbContext.SaveChangesAsync();

            return Ok(new {token = accessToken});
        }

        protected string GenerateJwtToken(AppUser user)
        {
            var claims = new Claim[]
            {
                new(JwtRegisteredClaimNames.Sub, user.Id),
                new(JwtRegisteredClaimNames.Jti, Guid.NewGuid().ToString()),
            };

            var key = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(_configuration["JwtSettings:Key"]!));
            var cred = new SigningCredentials(key, SecurityAlgorithms.HmacSha256);

            var token = new JwtSecurityToken(
                issuer: _configuration["JwtSettings:Issuer"],
                audience: _configuration["JwtSettings:Audience"],
                claims: claims,
                expires: DateTime.UtcNow.AddSeconds(60),
                signingCredentials: cred
            );

            return new JwtSecurityTokenHandler().WriteToken(token);
        }

        protected async Task<string> CreateTokens(HttpResponse response, AppUser user, bool remember)
        {
            string accessToken = GenerateJwtToken(user);
            string refreshToken = Convert.ToBase64String(RandomNumberGenerator.GetBytes(512));

            _dbContext.RefreshTokens.Add(new RefreshToken()
            {
                Token = refreshToken,
                User = user,
                ExpiresOn = remember ? DateTime.Now.AddYears(1) : DateTime.Now.AddMinutes(10)
            });
            response.Cookies.Append("auth-refresh-token", refreshToken, new CookieOptions()
            {
                HttpOnly = true,
                Secure = true,
                SameSite = SameSiteMode.Lax,
            });

            return accessToken;
        }

        protected async Task<string> UseTokens(HttpResponse response, RefreshToken refreshToken)
        {
            string accessToken = GenerateJwtToken(refreshToken.User);
            string newRefreshToken = Convert.ToBase64String(RandomNumberGenerator.GetBytes(512));

            refreshToken.Token = newRefreshToken;

            response.Cookies.Append("auth-refresh-token", newRefreshToken, new CookieOptions()
            {
                HttpOnly = true,
                Secure = true,
                SameSite = SameSiteMode.Lax,
            });

            return accessToken;
        }
    }
}
