using System.ComponentModel.DataAnnotations;
using Microsoft.AspNetCore.Identity;

namespace _1.Models
{
    public class AppUser : IdentityUser
    {

    }

    public class RefreshToken
    {
        public required string PublicToken { get; set; }

        public required string PrivateToken { get; set; }

        public required IdentityUser User { get; set; }

        public required DateTime ExpiresOn { get; set; }

        public bool IsInvalidated { get; set; } = false;
    }

    public record Login
    {
        
    }
}
