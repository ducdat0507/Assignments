using _5.Models;

namespace _5.Data;

public static class DbInitializer
{
    public static void Initialize(AppDbContext context)
    {
        context.Database.EnsureCreated();

        context.SaveChanges();
    }
}