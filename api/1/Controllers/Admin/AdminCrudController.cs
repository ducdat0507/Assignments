
using Microsoft.AspNetCore.Mvc;

namespace _1.Controllers.Admin
{
    [ApiController]
    [Route("Admin/[controller]")]
    public abstract class AdminCrudController<T> : ControllerBase
    {
        
    }
}