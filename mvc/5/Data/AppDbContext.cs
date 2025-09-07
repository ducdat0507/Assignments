namespace _5.Data;

using _5.Models;
using Microsoft.EntityFrameworkCore;

public class AppDbContext : DbContext
{
    public AppDbContext(DbContextOptions<AppDbContext> options) : base(options) { }

    public DbSet<Student> Students { get; set; }
    public DbSet<StudentBatch> StudentBatches { get; set; }

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        modelBuilder.Entity<Student>()
            .HasMany(s => s.EnrolledBatches)
            .WithMany(c => c.EnrolledStudents)
            .UsingEntity<StudentEnrollment>();
    }
        

}