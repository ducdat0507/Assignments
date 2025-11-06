using System.ComponentModel.DataAnnotations.Schema;
using Microsoft.EntityFrameworkCore;

namespace backend.Models;

[PrimaryKey("ProductId", "CategoryId")]
public class ProductCategory
{
    public int ProductId { get; set; }

    public int CategoryId { get; set; }
}