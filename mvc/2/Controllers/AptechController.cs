using System.Diagnostics;
using Microsoft.AspNetCore.Mvc;
using _2.Models;

namespace _2.Controllers;

public class AptechController : Controller
{
    private readonly ILogger<AptechController> _logger;

    private readonly List<Student> Students = [
        new Student {
            Id = 1,
            Name = "John Doe",
            Score = 90,
        },
        new Student {
            Id = 2,
            Name = "Jane Doe",
            Score = 80,
        },
        new Student {
            Id = 3,
            Name = "Foo",
            Score = 85,
        },
        new Student {
            Id = 4,
            Name = "Bar",
            Score = 95,
        },
    ];

    public AptechController(ILogger<AptechController> logger)
    {
        _logger = logger;
    }

    public IActionResult Index()
    {
        return View();
    }

    public IActionResult Student(ulong? id)
    {
        if (id == null)
        {
            return View(Students);
        }
        else
        {
            Student? student = Students.FirstOrDefault(x => x.Id == id.Value);
            if (student == null) return NotFound();
            return View(model: student, viewName: "StudentDetails");
        }
        
    }
}