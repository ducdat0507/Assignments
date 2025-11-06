using backend.Models;
using Microsoft.EntityFrameworkCore;

namespace backend.Database;

public class AppDbContext : DbContext
{
    public AppDbContext(DbContextOptions<AppDbContext> options) : base(options)
    {

    }

    public DbSet<Category> Categories;
    public DbSet<Product> Products;
    public DbSet<ProductCategory> ProductCategories;

    protected override void OnModelCreating(ModelBuilder builder)
    {
        builder.Entity<Product>();
        builder.Entity<Product>()
            .HasMany(m => m.Categories)
            .WithMany(m => m.Products)
            .UsingEntity<ProductCategory>();
    }
}