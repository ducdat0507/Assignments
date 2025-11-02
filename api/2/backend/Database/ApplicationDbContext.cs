using backend.Models;
using Microsoft.EntityFrameworkCore;

namespace backend.Database;

public class ApplicationDbContext : DbContext
{
    public ApplicationDbContext() {}

    public DbSet<Author> Authors;
    public DbSet<Book> Books;
    public DbSet<Genre> Genres;
    
}