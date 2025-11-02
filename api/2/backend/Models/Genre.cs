using System.ComponentModel.DataAnnotations;

namespace backend.Models;

public class Genre
{
    [Key]
    public int GenreId { get; set; }

    [Required]
    [MaxLength(256)]
    public required string Name { get; set; }

    [MaxLength(4096)]
    public string? Description { get; set; }
}