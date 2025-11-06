
using System.ComponentModel.DataAnnotations;

namespace backend.Models;

public class Product
{
    public int Id { get; set; }

    [Required]
    public required string Name { get; set; }

    public decimal Price { get; set; }

    public List<Category> Categories { get; set; } = [];
}