using _5.Models;
using Bogus;

namespace _5.Data;

public static class DbInitializer
{
    public static void Initialize(AppDbContext context)
    {
        context.Database.EnsureCreated();

        if (context.Students.FirstOrDefault() == null)
        {
            int currentYear = DateTime.Today.Year;
            var bogus = new Faker<Student>("vi")
                .RuleFor(o => o.Gender, o => o.PickRandom<Gender>())
                .RuleFor(o => o.FirstName, (f, o) => f.Name.FirstName((Bogus.DataSets.Name.Gender)o.Gender!))
                .RuleFor(o => o.SecondName, (f, o) => f.Name.LastName((Bogus.DataSets.Name.Gender)o.Gender!))
                .RuleFor(o => o.BirthDate, (f, o) => f.Date.BetweenDateOnly(new DateOnly(currentYear - 25, 1, 1), new DateOnly(currentYear - 18, 12, 31)))
                .RuleFor(o => o.PhoneNumber, (f, o) => f.Phone.PhoneNumber())
                .RuleFor(o => o.EmailAddress, (f, o) => f.Internet.Email(o.FirstName, o.SecondName))
            ;
            for (int a = 0; a < 10000; a++)
            {
                context.Students.Add(bogus.Generate());
                context.SaveChanges();
            }
        }

        context.SaveChanges();
    }
}