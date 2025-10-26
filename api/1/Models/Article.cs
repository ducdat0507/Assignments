using System.ComponentModel.DataAnnotations;

namespace _1.Models
{
    public class Article : IEntity
    {
        public ulong Id { get; set; }

        [MaxLength(256)]
        public string Title { get; set; }

        public string Subtitle { get; set; }

        public string Body { get; set; }
    }


    public class ArticleGet : IEntityGet<Article, ArticleGet>
    {
        public ulong Id { get; set; }

        [MaxLength(256)]
        public string Title { get; set; }

        public string Subtitle { get; set; }

        public string Body { get; set; }

        public ArticleGet FromEntity(Article entity)
        {
            Title = entity.Title;
            Subtitle = entity.Subtitle;
            Body = entity.Body;
            return this;
        }
    }

    public class ArticlePost : IEntityPost<Article>
    {
        [MaxLength(256)]
        public string Title { get; set; }

        public string Subtitle { get; set; }

        public string Body { get; set; }
        
        public Article ToEntity()
        {
            return new Article
            {
                Title = Title,
                Subtitle = Subtitle,
                Body = Body,
            };
        }
    }

    public class ArticleDelete : IEntityDelete<Article>
    {
        public ulong Id { get; set; }
    }

    public class ArticleQuery : IEntityQuery<Article>
    {
        public IEnumerable<Article> Apply(IEnumerable<Article> query)
        {
            return query;
        }
    }
}