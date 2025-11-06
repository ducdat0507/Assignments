using System.ComponentModel.DataAnnotations;

namespace backend.Models;

public class Category
{
    public int Id { get; set; }

    [Required]
    public required string CategoryName { get; set; }

    public List<Product> Products { get; set; } = [];
}