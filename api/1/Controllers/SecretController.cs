using Microsoft.AspNetCore.Mvc;
using _1.Models;
using Microsoft.AspNetCore.Identity.Data;
using Microsoft.AspNetCore.Identity;
using System.Security.Cryptography;
using Microsoft.AspNetCore.Cryptography.KeyDerivation;
using Microsoft.EntityFrameworkCore;
using Microsoft.AspNetCore.Authorization;
using System.IdentityModel.Tokens.Jwt;

namespace _1.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class SecretController : ControllerBase
    {
        private readonly ApplicationDbContext _dbContext;
        private readonly ILogger<AuthController> _logger;

        public SecretController(ApplicationDbContext dbContext, ILogger<AuthController> logger)
        {
            _dbContext = dbContext;
            _logger = logger;
        }

        [HttpGet]
        [Authorize(Roles="User")]
        public async Task<IActionResult> Get()
        {
            var username = HttpContext.User.Claims.FirstOrDefault(x => x.Type == JwtRegisteredClaimNames.Sub);
            if (username == null) return Forbid("Bearer");
            var user = await _dbContext.Users.FirstOrDefaultAsync(x => x.Id == username.Value);
            if (user == null) return Forbid("Bearer");

            return Ok("this is super secret string");
        }
    }
}
