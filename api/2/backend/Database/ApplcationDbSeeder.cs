using backend.Models;

namespace backend.Database;

public static class ApplicationDbSeeder
{
    public static void Seed(ApplicationDbContext dbContext)
    {
        // Seed Genres
        if (!dbContext.Genres.Any())
        {
            var genres = new List<Genre>
            {
                new() { Name = "Science Fiction", Description = "Fiction dealing with futuristic settings." },
                new() { Name = "Fantasy", Description = "Fiction with magical or supernatural elements." },
                new() { Name = "Non-Fiction", Description = "Based on facts and real events." }
            };
            dbContext.Genres.AddRange(genres);
            dbContext.SaveChanges();
        }

        // Seed Authors
        if (!dbContext.Authors.Any())
        {
            var authors = new List<Author>
            {
                new() { Name = "Isaac Asimov", Biography = "Isaac Asimov was a prolific science fiction author and professor of biochemistry." },
                new() { Name = "J.K. Rowling", Biography = "J.K. Rowling is a British author, best known for the Harry Potter series." },
                new() { Name = "Malcolm Gladwell", Biography = "Malcolm Gladwell is a Canadian journalist and non-fiction writer." }
            };
            dbContext.Authors.AddRange(authors);
            dbContext.SaveChanges();
        }

        // Seed Books
        if (!dbContext.Books.Any())
        {
            var sciFi = dbContext.Genres.FirstOrDefault(g => g.Name == "Science Fiction");
            var fantasy = dbContext.Genres.FirstOrDefault(g => g.Name == "Fantasy");
            var nonFiction = dbContext.Genres.FirstOrDefault(g => g.Name == "Non-Fiction");

            var asimov = dbContext.Authors.FirstOrDefault(a => a.Name == "Isaac Asimov");
            var rowling = dbContext.Authors.FirstOrDefault(a => a.Name == "J.K. Rowling");
            var gladwell = dbContext.Authors.FirstOrDefault(a => a.Name == "Malcolm Gladwell");

            var books = new List<Book>
            {
                new() { Title = "Foundation", Genre = sciFi!, Author = asimov!, PublicationYear = 1951 },
                new() { Title = "Harry Potter and the Sorcerer's Stone", Genre = fantasy!, Author = rowling!, PublicationYear = 1997 },
                new() { Title = "Outliers", Genre = nonFiction!, Author = gladwell!, PublicationYear = 2008 }
            };
            dbContext.Books.AddRange(books);
            dbContext.SaveChanges();
        }
    }
}