using Microsoft.AspNetCore.Mvc;
using _1.Models;
using Microsoft.AspNetCore.Identity.Data;
using Microsoft.AspNetCore.Identity;

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

        [HttpPost]
        public async IActionResult Login(string username, string password, bool remember)
        {
            var user = _dbContext.Users.SingleOrDefault(x => x.UserName == username);
            if (user == null) return NotFound("User not found");

            bool isValidCredentials = await _userManager.CheckPasswordAsync(user, password);
            if (!isValidCredentials) return Forbid("Invalid password");

            return Ok();
        }
    }
}
