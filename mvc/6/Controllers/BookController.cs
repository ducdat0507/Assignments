using System.Diagnostics;
using Microsoft.AspNetCore.Mvc;
using _6.Models;
using _6.Data;
using Microsoft.AspNetCore.Http.HttpResults;

namespace _6.Controllers;

public class BookController : Controller
{
    private readonly ILogger<HomeController> _logger;
    private readonly AppDbContext _database;

    public BookController(ILogger<HomeController> logger, AppDbContext database)
    {
        _logger = logger;
        _database = database;
    }

    public IActionResult Index(int page = 1)
    {
        const int ItemsPerPage = 5;
        var items = _database.Books
            .Skip((page - 1) * ItemsPerPage)
            .Take(ItemsPerPage);

        return View(items);
    }

    public IActionResult Add(Book book)
    {
        return NotFound();
    }
}
