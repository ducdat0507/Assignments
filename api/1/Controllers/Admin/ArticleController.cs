
using _1.Models;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace _1.Controllers.Admin
{
    [ApiController]
    [Route("Admin/[controller]")]
    [Authorize(Roles = "Manager")]
    public class ArticleController : AdminCrudController<Article, ApplicationDbContext>
    {
        public ArticleController(ApplicationDbContext dbContext) : base(dbContext)
        {

        }
        
        [HttpGet]
        public IActionResult GetList(IEntityPaging<Article> paging = null, IEntityQuery<Article>? query = null)
        {
            return GetListInternal(_dbContext.Articles, paging, query);
        }

        [HttpPost]
        public IActionResult Post(IEntityPost<Article> data)
        {
            return PostInternal(_dbContext.Articles, data, new ArticleGet());
        }

        [HttpDelete]
        public IActionResult Delete(IEntityDelete<Article> data)
        {
            return DeleteInternal(_dbContext.Articles, data);
        }
    }
}