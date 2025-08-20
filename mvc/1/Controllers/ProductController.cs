using System.Diagnostics;
using Microsoft.AspNetCore.Mvc;
using _1.Models;

namespace _1.Controllers;

public class ProductController : Controller
{
    private List<Product> Products;

    public ProductController()
    {
        Products = [
            new Product {
                Id = 1,
                Name = "Model",
                Price = 3.99
            },
            new Product {
                Id = 2,
                Name = "View",
                Price = 4.99
            },
            new Product {
                Id = 3,
                Name = "Controller",
                Price = 5.99
            },
        ];
    }

    public IActionResult Index()
    {
        return View(Products);
    }

    public IActionResult Details(ulong id)
    {
        Product? product = Products.FirstOrDefault(p => p?.Id == id, null);
        if (product == null) return NotFound();
        return View(product!);
    }

    [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
    public IActionResult Error()
    {
        return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
    }
}
