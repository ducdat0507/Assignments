
using System.ComponentModel.DataAnnotations;

namespace _6.Models
{
    public class Book
    {
        public long? Id { get; set; }

        public string? Name { get; set; }

        public BookCategory? Category { get; set; }

        public Author? Author { get; set; }

        public int? PublishedYear { get; set; }

        public int? AmountInStorage { get; set; }

    }
}