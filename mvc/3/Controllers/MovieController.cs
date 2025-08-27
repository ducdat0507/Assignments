using System.Diagnostics;
using Microsoft.AspNetCore.Mvc;
using _3.Models;

namespace _3.Controllers;

public class MovieController : Controller
{
    static readonly List<MovieModel> Movies = [
        new MovieModel {
            Id = 1,
            Name = "A Minecraft Movie",
            Synopsis = "Four misfits are suddenly pulled through a mysterious portal into a bizarre cubic wonderland that thrives on imagination. To get back home they'll have to master this world while embarking on a quest with an unexpected expert crafter.",
            Cast = "Jason Momoa, Jack Black, Danielle Brooks, Emma Myers, Sebastian Hansen",
            TicketPrice = 59.99m,
            TrailerLink = "https://www.youtube-nocookie.com/embed/wJO_vIDZn-I",
            WatchLink = "https://www.youtube-nocookie.com/embed/dQw4w9WgXcQ",
        },
        new MovieModel {
            Id = 2,
            Name = "A Minecraft Movie",
            Synopsis = "Four misfits are suddenly pulled through a mysterious portal into a bizarre cubic wonderland that thrives on imagination. To get back home they'll have to master this world while embarking on a quest with an unexpected expert crafter.",
            Cast = "Jason Momoa, Jack Black, Danielle Brooks, Emma Myers, Sebastian Hansen",
            TicketPrice = 59.99m,
            TrailerLink = "https://www.youtube-nocookie.com/embed/wJO_vIDZn-I",
            WatchLink = "https://www.youtube-nocookie.com/embed/dQw4w9WgXcQ",
        },
        new MovieModel {
            Id = 3,
            Name = "A Minecraft Movie",
            Synopsis = "Four misfits are suddenly pulled through a mysterious portal into a bizarre cubic wonderland that thrives on imagination. To get back home they'll have to master this world while embarking on a quest with an unexpected expert crafter.",
            Cast = "Jason Momoa, Jack Black, Danielle Brooks, Emma Myers, Sebastian Hansen",
            TicketPrice = 59.99m,
            TrailerLink = "https://www.youtube-nocookie.com/embed/wJO_vIDZn-I",
            WatchLink = "https://www.youtube-nocookie.com/embed/dQw4w9WgXcQ",
        },
        new MovieModel {
            Id = 4,
            Name = "A Minecraft Movie",
            Synopsis = "Four misfits are suddenly pulled through a mysterious portal into a bizarre cubic wonderland that thrives on imagination. To get back home they'll have to master this world while embarking on a quest with an unexpected expert crafter.",
            Cast = "Jason Momoa, Jack Black, Danielle Brooks, Emma Myers, Sebastian Hansen",
            TicketPrice = 59.99m,
            TrailerLink = "https://www.youtube-nocookie.com/embed/wJO_vIDZn-I",
            WatchLink = "https://www.youtube-nocookie.com/embed/dQw4w9WgXcQ",
        },
        new MovieModel {
            Id = 5,
            Name = "A Minecraft Movie",
            Synopsis = "Four misfits are suddenly pulled through a mysterious portal into a bizarre cubic wonderland that thrives on imagination. To get back home they'll have to master this world while embarking on a quest with an unexpected expert crafter.",
            Cast = "Jason Momoa, Jack Black, Danielle Brooks, Emma Myers, Sebastian Hansen",
            TicketPrice = 59.99m,
            TrailerLink = "https://www.youtube-nocookie.com/embed/wJO_vIDZn-I",
            WatchLink = "https://www.youtube-nocookie.com/embed/dQw4w9WgXcQ",
        },
    ];

    private readonly ILogger<MovieController> _logger;

    public MovieController(ILogger<MovieController> logger)
    {
        _logger = logger;
    }

    public IActionResult Index(ulong? id)
    {
        if (id != null)
        {
            MovieModel? movie = Movies.FirstOrDefault(m => m.Id == id);
            if (movie != null) return View(model: movie, viewName: "Details");
            else return NotFound();
        }
        else
        {
            return View(Movies);
        }
    }
}
