using System.ComponentModel.DataAnnotations;

namespace _1.Models
{
    public class Article
    {
        public ulong Id { get; set; }

        [MaxLength(256)]
        public string Title { get; set; }

        public string Subtitle { get; set; }

        public string Body { get; set; }
    }
}