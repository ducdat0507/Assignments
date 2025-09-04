using System.Diagnostics;
using Microsoft.AspNetCore.Mvc;
using _4.Models;
using _4.Data;
using Microsoft.EntityFrameworkCore;

namespace _4.Controllers;

public class HomeController : Controller
{
    private readonly ILogger<HomeController> _logger;
    private readonly AppDbContext _database;

    public HomeController(ILogger<HomeController> logger, AppDbContext database)
    {
        _logger = logger;
        _database = database;
    }

    public IActionResult Index()
    {
        return View(_database.ProductCategories.Include(e => e.Products));
    }

    public IActionResult Category(ulong id)
    {
        var thing = _database.ProductCategories.Where(e => e.Id == id).FirstOrDefault();
        if (thing == null) return NotFound();
        return View();
    }

    public IActionResult Detail(ulong id)
    {
        var thing = _database.Products.Where(e => e.Id == id).FirstOrDefault();
        if (thing == null) return NotFound();
        return View();
    }


    public IActionResult Privacy()
    {
        return View();
    }
}
