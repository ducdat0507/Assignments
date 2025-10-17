using _1.Models;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;

public static class ApplicationDbSeeder
{
    public static void Seed(ApplicationDbContext dbContext)
    {
        if (dbContext.Roles.SingleOrDefault() == null)
        {
            dbContext.Roles.Add(new IdentityRole()
            {
                Name = "User",
                NormalizedName = "USER",
            });
            dbContext.Roles.Add(new IdentityRole()
            {
                Name = "Admin",
                NormalizedName = "ADMIN",
            });

            dbContext.SaveChanges();
        }
    }

}