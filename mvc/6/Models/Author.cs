namespace _6.Models
{
    public class Author
    {
        public long? Id { get; set; }

        public string? Name { get; set; }

        public IList<Book>? Books { get; set; }
    }
}