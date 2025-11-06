using Microsoft.EntityFrameworkCore;

namespace backend.DAL;

public interface IRepository<TEntity> where TEntity : class
{
    public IEnumerable<TEntity> GetAll();

    public TEntity? GetById(object id);

    public void Insert(TEntity entity);

    public void Update(TEntity entity);

    public void Delete(object id);

    public void Delete(TEntity entity);
}