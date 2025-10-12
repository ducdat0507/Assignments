using Microsoft.AspNetCore.Mvc;
using _1.Models;
using Microsoft.AspNetCore.Identity.Data;
using Microsoft.AspNetCore.Identity;
using System.Security.Cryptography;
using Microsoft.AspNetCore.Cryptography.KeyDerivation;
using Microsoft.EntityFrameworkCore;
using Microsoft.AspNetCore.Authorization;

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
            return Ok("this is super secret string");
        }
    }
}
