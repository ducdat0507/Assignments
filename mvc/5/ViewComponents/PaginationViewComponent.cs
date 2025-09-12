using Microsoft.AspNetCore.Mvc;

namespace _5.ViewComponents
{
    public class PaginationViewComponent : ViewComponent
    {

        public PaginationViewComponent()
        {
        }

        public IViewComponentResult Invoke(int currentPage, int totalPages, int pageSize)
        {
            ViewData["currentPage"] = currentPage;
            ViewData["totalPages"] = totalPages;
            ViewData["pageSize"] = pageSize;
            return View();
        }
    }
}
