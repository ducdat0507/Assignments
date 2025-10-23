
using _1.Models;
using Microsoft.AspNetCore.Mvc;

namespace _1.Controllers.Admin
{
    [ApiController]
    [Route("Admin/[controller]")]
    public class ArticleController : AdminCrudController<Article>
    {
    }
}