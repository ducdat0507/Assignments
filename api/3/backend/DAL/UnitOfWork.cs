using backend.Database;
using backend.Models;

namespace backend.DAL;

public class UnitOfWork : IDisposable
{
    protected AppDbContext dbContext { get; private set; }

    private IRepository<Category> categoryRepository;
    private IRepository<Product> productCategory;

    private bool disposed = false;


    public IRepository<Category> CategoryRepository
    {
        get
        {
            return categoryRepository ??= new GenericRepository<Category>(dbContext);
        }
    }
    public IRepository<Product> ProductRepository
    {
        get
        {
            return productRepository ??= new GenericRepository<Product>(dbContext);
        }
    }
    
    public int SaveChanges()
    {
        return dbContext.SaveChanges();
    }

    protected virtual void Dispose(bool disposing)
    {
        if (!disposed)
        {
            if (disposing)
            {
                dbContext.Dispose();
            }
        }
        disposed = true;
    }

    public void Dispose()
    {
        Dispose(true);
        GC.SuppressFinalize(this);
    }
}