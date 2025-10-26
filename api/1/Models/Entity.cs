using System.ComponentModel.DataAnnotations;

namespace _1.Models
{
    public interface IEntity
    {
        public ulong Id { get; set; }
    }

    public interface IEntityGet<T, TSelf> where T : IEntity where TSelf : IEntityGet<T, TSelf>
    {
        public TSelf FromEntity(T entity);
    }

    public interface IEntityPost<T> where T : IEntity
    {
        public T ToEntity();
    }

    public interface IEntityDelete<T> where T : IEntity
    {
        public ulong Id { get; set; }
    }

    public interface IEntityQuery<T> where T : IEntity
    {
        public IEnumerable<T> Apply(IEnumerable<T> query);
    }

    public class IEntityPaging<T> : IEntityQuery<T> where T : IEntity
    {
        public int PageSize { get; set; } = 30;

        public ulong? StartId { get; set; } = null;

        public IEnumerable<T> Apply(IEnumerable<T> query)
        {
            query = query.OrderBy(x => x.Id);
            if (StartId != null) query = query.SkipWhile(x => x.Id > StartId);
            query.Take(PageSize);
            return query;
        }
    }
}