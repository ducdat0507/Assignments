
using System.ComponentModel.DataAnnotations;
using Microsoft.EntityFrameworkCore;

namespace backend.Models;

public class Author
{
    [Key]
    public int AuthorId { get; set; }

    [Required]
    [MaxLength(128)]
    public required string Name { get; set; }
    
    [Required]
    [MaxLength(4096)]
    public required string Biography { get; set; }

}