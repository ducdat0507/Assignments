using Microsoft.EntityFrameworkCore;

namespace backend.DAL;

public class GenericRepository<TEntity> : IRepository<TEntity> where TEntity : class
{
    internal DbContext dbContext;
    internal DbSet<TEntity> dbSet;

    public GenericRepository(DbContext dbContext)
    {
        this.dbContext = dbContext;
        this.dbSet = dbContext.Set<TEntity>();
    }

    public IEnumerable<TEntity> GetAll()
    {
        return dbSet;
    }

    public TEntity? GetById(object id)
    {
        return dbSet.Find(id);
    }

    public void Insert(TEntity entity)
    {
        dbSet.Add(entity);
    }

    public void Update(TEntity entity)
    {
        dbSet.Attach(entity);
        dbContext.Entry(entity).State = EntityState.Modified;
    }

    public void Delete(object id)
    {
        TEntity? entity = dbSet.Find(id);
        if (entity != null) dbSet.Remove(entity);
    }

    public void Delete(TEntity entity)
    {
        if (dbContext.Entry(entity).State == EntityState.Detached)
        {
            dbSet.Attach(entity);
        }
        dbSet.Remove(entity);
    }
}