using _4.Models;

namespace _4.Data;

public static class DbInitializer
{
    public static void Initialize(AppDbContext context)
    {
        context.Database.EnsureCreated();

        if (context.Products.Any()) return;

        ProductCategory[] categories = [
            new ProductCategory { Id = 1, Name = "Phones" },
            new ProductCategory { Id = 2, Name = "Laptops" },
        ];
        foreach (var item in categories) context.ProductCategories.Add(item);
        context.SaveChanges();

        Product[] products = [
            new Product { Id = 1, CategoryId = 1, ThumbnailUrl = "https://placehold.co/300x300", Name = "iPhone 16 Pro Max" },
            new Product { Id = 2, CategoryId = 1, ThumbnailUrl = "https://placehold.co/300x300", Name = "Samsung Galaxy S26 Ultra" },
            new Product { Id = 3, CategoryId = 1, ThumbnailUrl = "https://placehold.co/300x300", Name = "Google Pixel 10 Pro Fold" },
            new Product { Id = 4, CategoryId = 1, ThumbnailUrl = "https://placehold.co/300x300", Name = "Oppo F31 Pro" },
            new Product { Id = 5, CategoryId = 2, ThumbnailUrl = "https://placehold.co/300x300", Name = "Microsoft Surface (2025)" },
            new Product { Id = 6, CategoryId = 2, ThumbnailUrl = "https://placehold.co/300x300", Name = "Apple MacBook Air M5 15-inch" },
        ];
        foreach (var item in products) context.Products.Add(item);
        context.SaveChanges();
    }
}