
using _1.Models;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace _1.Controllers.Admin
{
    [ApiController]
    [Route("Admin/[controller]")]
    public abstract class AdminCrudController<TEntity, TDbContext> : ControllerBase 
        where TEntity : class, IEntity
        where TDbContext : DbContext
    {
        protected TDbContext _dbContext;

        public AdminCrudController(TDbContext dbContext)
        {
            _dbContext = dbContext;
        }

        protected IActionResult GetListInternal(DbSet<TEntity> table, IEntityPaging<TEntity> paging, IEntityQuery<TEntity>? query = null)
        {
            IEnumerable<TEntity> enumerable = table;
            if (query != null) enumerable = query.Apply(enumerable);
            enumerable = paging.Apply(enumerable);

            return Ok(new {
                count = table.Count(),
                data = enumerable,
            });
        }

        protected IActionResult PostInternal<TGet>(DbSet<TEntity> table, IEntityPost<TEntity> data, IEntityGet<TEntity, TGet> result)
            where TGet : IEntityGet<TEntity, TGet>
        {
            TEntity entity = data.ToEntity();
            table.Add(entity);
            _dbContext.SaveChanges();
            return Ok(new {
                data = result.FromEntity(entity)
            });
        }

        protected IActionResult DeleteInternal(DbSet<TEntity> table, IEntityDelete<TEntity> data)
        {
            table.Where(x => x.Id == data.Id).ExecuteDelete();
            return Ok();
        }
    }
}