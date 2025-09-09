using Microsoft.EntityFrameworkCore;

namespace _5.Models;

public class Student
{
    public ulong Id { get; set; }

    public string? FirstName { get; set; }

    public string? SecondName { get; set; }

    public Gender? Gender { get; set; }

    public DateOnly? BirthDate { get; set; }

    public string? PhoneNumber { get; set; }

    public string? EmailAddress { get; set; }

    public List<StudentBatch> EnrolledBatches { get; set; } = null!;
}

public class StudentBatch
{
    public ulong Id { get; set; }

    public string? Name { get; set; }

    public List<Student> EnrolledStudents { get; set; } = null!;
}

public class StudentEnrollment
{
    public ulong StudentId { get; set; }

    public ulong StudentBatchId { get; set; }
}

public enum Gender
{
    Male,
    Female
}