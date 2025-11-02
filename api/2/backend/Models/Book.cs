using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace backend.Models;

public class Book
{
    [Key]
    public int BookId { get; set; }

    [Required]
    [MaxLength(256)]
    public required string Title { get; set; }

    [Required]
    [ForeignKey("Genre")]
    public required Genre Genre { get; set; }

    public int? PublicationYear { get; set; }

    [Required]
    [ForeignKey("AuthorId")]
    public required Author Author { get; set; }
}