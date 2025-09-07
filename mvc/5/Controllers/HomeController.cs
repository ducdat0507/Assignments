using Microsoft.AspNetCore.Mvc;
using _5.Data;
using Microsoft.EntityFrameworkCore;

namespace _5.Controllers;

public class HomeController : Controller
{
    private readonly ILogger<HomeController> _logger;
    private readonly AppDbContext _database;

    const int ItemsPerPage = 20;

    public HomeController(ILogger<HomeController> logger, AppDbContext database)
    {
        _logger = logger;
        _database = database;
    }

    public IActionResult Index(int page = 1)
    {
        return View(
            _database.Students
                .Include(s => s.EnrolledBatches)
                .Skip((page - 1) * ItemsPerPage)
                .Take(20)
        );
    }
}
