using Microsoft.EntityFrameworkCore;

namespace _4.Models;

public class Product
{
    public ulong Id { get; set; }

    public string? Name { get; set; }

    public decimal Price { get; set; }

    public string? ThumbnailUrl { get; set; }

    public ulong CategoryId { get; set; }

    public ProductCategory Category { get; set; } = null!;
}

public class ProductCategory
{
    public ulong Id { get; set; }

    public string? Name { get; set; }

    public ICollection<Product> Products { get; } = new List<Product>(); 
}