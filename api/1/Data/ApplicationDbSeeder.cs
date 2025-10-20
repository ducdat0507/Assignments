using _1.Models;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;

public static class ApplicationDbSeeder
{
    public static void SeedAsync(ApplicationDbContext dbContext)
    {
        dbContext.Database.EnsureCreated();
    }

    public static async Task SeedRolesAsync(RoleManager<IdentityRole> roleManager)
    {
        string[] roles = ["Admin", "Manager", "User"];

        List<Task> tasks = new();

        foreach (var role in roles)
        {
            tasks.Add(Task.Run(async () =>
            {
                Console.WriteLine("Creating role " + role);
                if (!await roleManager.RoleExistsAsync(role))
                {
                    await roleManager.CreateAsync(new IdentityRole(role));
                    Console.WriteLine("Created role " + role);
                }
            }));
        }

        CancellationToken cancellationToken = new();
        foreach (Task task in tasks) await task.WaitAsync(cancellationToken);
    }

}