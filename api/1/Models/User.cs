using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using Microsoft.AspNetCore.Identity;
using Microsoft.EntityFrameworkCore;

namespace _1.Models
{
    public class AppUser : IdentityUser
    {

    }

    public class RefreshToken
    {
        public ulong Id { get; set; }

        public required string Token { get; set; }

        [ForeignKey("UserId")]
        public required AppUser User { get; set; }

        public required DateTime ExpiresOn { get; set; }

        public bool IsRevoked { get; set; } = false;
    }
}
