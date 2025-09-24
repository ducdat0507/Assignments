using Microsoft.EntityFrameworkCore;
using _7.Models;

namespace _7.Data
{
    public class AppDbContext : DbContext
    {
        public AppDbContext(DbContextOptions<AppDbContext> options) : base(options) { }

        public DbSet<Student> Students { get; set; }
        public DbSet<StudentBatch> StudentBatches { get; set; }
        public DbSet<StudentEnrollment> StudentEnrollments { get; set; }
        public DbSet<StudentMark> StudentMarks { get; set; }
        public DbSet<Course> Courses { get; set; }
        public DbSet<Subject> Subjects { get; set; }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<StudentEnrollment>()
                .HasOne(e => e.Student)
                .WithMany()
                .HasForeignKey("StudentId");

            modelBuilder.Entity<StudentEnrollment>()
                .HasOne(e => e.Batch)
                .WithMany()
                .HasForeignKey("BatchId");

            modelBuilder.Entity<StudentEnrollment>()
                .HasKey("StudentId", "BatchId");

            modelBuilder.Entity<StudentMark>()
                .HasOne(m => m.Owner)
                .WithMany(e => e.Marks)
                .HasForeignKey("StudentId", "BatchId");

            modelBuilder.Entity<StudentMark>()
                .HasOne(m => m.Subject)
                .WithMany()
                .HasForeignKey("SubjectId");

            modelBuilder.Entity<Student>()
                .HasMany(s => s.EnrolledBatches)
                .WithMany(b => b.Students)
                .UsingEntity<StudentEnrollment>(
                    j => j.HasOne(e => e.Batch).WithMany().HasForeignKey("BatchId"),
                    j => j.HasOne(e => e.Student).WithMany().HasForeignKey("StudentId"),
                    j => j.HasKey("StudentId", "BatchId")
                );

            modelBuilder.Entity<StudentBatch>()
                .HasOne(b => b.EnrolledCourse)
                .WithMany()
                .HasForeignKey("CourseId");
        }
    }
}