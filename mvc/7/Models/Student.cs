namespace _7.Models;

public class Student
{
    public int Id { get; set; }

    public string? Name { get; set; }

    public List<StudentBatch> EnrolledBatches { get; set; }
}

public class StudentBatch
{
    public int Id { get; set; }

    public string? Name { get; set; }

    public required Course EnrolledCourse { get; set; }

    public List<Student> Students { get; set; }
}

public class StudentEnrollment
{
    public required Student Student { get; set; }

    public required StudentBatch Batch { get; set; }
    
    public List<StudentMark> Marks { get; set; }
}

public class StudentMark
{
    public required StudentEnrollment Owner { get; set; }

    public required Subject Subject { get; set; }
    
    public float Score { get; set; }
    
    public int Weight { get; set; }
}

public class Course
{
    public int Id { get; set; }

    public string? Name { get; set; }

    public List<Subject> Subjects { get; set; }
}

public class Subject
{
    public int Id { get; set; }

    public string? Name { get; set; }
}

