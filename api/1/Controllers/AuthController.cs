using Microsoft.AspNetCore.Mvc;
using _1.Models;
using Microsoft.AspNetCore.Identity.Data;
using Microsoft.AspNetCore.Identity;
using System.Security.Cryptography;
using Microsoft.AspNetCore.Cryptography.KeyDerivation;
using Microsoft.EntityFrameworkCore;

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

        public AuthController(ApplicationDbContext dbContext, UserManager<AppUser> userManager, SignInManager<AppUser> signInManager, ILogger<AuthController> logger)
        {
            _dbContext = dbContext;
            _userManager = userManager;
            _signInManager = signInManager;
            _logger = logger;
        }

        [HttpPost("[action]")]
        public async Task<IActionResult> Login(string username, string password, bool remember)
        {
            var user = await _userManager.FindByNameAsync(username);
            if (user == null) return NotFound("User not found");

            bool isValidCredentials = await _userManager.CheckPasswordAsync(user, password);
            if (!isValidCredentials) return Forbid("Invalid password");

            await _signInManager.SignInAsync(user, false);
            await CreateRefreshToken(Response, user, remember);
            await _dbContext.SaveChangesAsync();

            return Ok();
        }

        [HttpPost("[action]")]
        public async Task<IActionResult> Register(string username, string password, bool remember)
        {
            var user = new AppUser()
            {
                UserName = username,
            };

            var result = await _userManager.CreateAsync(user, password);
            if (!result.Succeeded)
            {
                return BadRequest(result.Errors);
            }
            await _userManager.AddToRoleAsync(user, "User");
            
            await _signInManager.SignInAsync(user, false);
            await CreateRefreshToken(Response, user, remember);
            await _dbContext.SaveChangesAsync();

            return Ok();
        }

        [HttpPost("[action]")]
        public async Task<IActionResult> Refresh()
        {
            if (!Request.Cookies.TryGetValue("auth-refresh-token", out string? token) || token == null)
            {
                return Forbid("Invalid credentials");
            }

            var tokenData = _dbContext.RefreshTokens.Include(x => x.User).SingleOrDefault(x => x.Token == token);
            if (tokenData == null)
            {
                return Forbid("Invalid credentials");
            }

            var user = tokenData.User;
            tokenData.Token = token = Convert.ToBase64String(RandomNumberGenerator.GetBytes(512));
            Response.Cookies.Append("auth-refresh-token", token, new CookieOptions()
            {
                HttpOnly = true,
            });

            await _signInManager.SignInAsync(user, false);
            await _dbContext.SaveChangesAsync();

            return Ok();
        }

        protected async Task CreateRefreshToken(HttpResponse response, AppUser user, bool remember)
        {
            await _signInManager.SignInAsync(user, false);

            string token = Convert.ToBase64String(RandomNumberGenerator.GetBytes(512));

            _dbContext.RefreshTokens.Add(new RefreshToken()
            {
                Token = token,
                User = user,
                ExpiresOn = remember ? DateTime.Now.AddYears(1) : DateTime.Now.AddMinutes(10)
            });

            response.Cookies.Append("auth-refresh-token", token, new CookieOptions()
            {
                HttpOnly = true,
            });
        }
    }
}
