using System.ComponentModel.DataAnnotations;

namespace _1.Models
{
    public interface IEntity
    {
        public ulong Id { get; set; }
    }

    public interface IEntityGet<T> where T : IEntity
    {
        public IEntityGet<T> FromEntity(T entity);
    }

    public interface IEntityPost<T> where T : IEntity
    {
        public T ToEntity();
    }

    public interface IEntityDelete<T> where T : IEntity
    {
        public ulong Id { get; set; }
    }
}