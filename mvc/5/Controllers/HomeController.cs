using Microsoft.AspNetCore.Mvc;
using _5.Data;
using Microsoft.EntityFrameworkCore;

namespace _5.Controllers;

public class HomeController : Controller
{
    private readonly ILogger<HomeController> _logger;
    private readonly AppDbContext _database;

    public HomeController(ILogger<HomeController> logger, AppDbContext database)
    {
        _logger = logger;
        _database = database;
    }

    public IActionResult Index(int page = 1, int pageSize = 20)
    {
        int totalPages = (int)Math.Ceiling(_database.Students.Count() / (double)pageSize);
        if (page < 1) page = 1;
        if (page > totalPages) page = totalPages;

        ViewData["CurrentPage"] = page;
        ViewData["TotalPages"] = totalPages;
        ViewData["PageSize"] = pageSize;

        return View(
            _database.Students
                .OrderBy(x => x.Id)
                .Skip((page - 1) * pageSize)
                .Take(pageSize)
                .ToList()
        );
    }
}
