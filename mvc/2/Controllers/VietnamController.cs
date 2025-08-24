using System.Diagnostics;
using Microsoft.AspNetCore.Mvc;
using _2.Models;

namespace _2.Controllers;

public class VietnamController : Controller
{
    private readonly ILogger<VietnamController> _logger;

    private readonly List<string> CityList = [
        "Cao Bằng",
        "Lạng Sơn",
        "Phú Thọ",
        "Quảng Ninh",
        "Thái Nguyên",
        "Tuyên Quang",
        "Lào Cai",
        "Điện Biên",
        "Lai Châu",
        "Sơn La",
        "Bắc Ninh",
        "Hưng Yên",
        "Ninh Bình",
        "Hà Nội",
        "Hải Phòng",
        "Hà Tĩnh",
        "Nghệ An",
        "Quảng Trị",
        "Thanh Hóa",
        "Huế",
        "Đắk Lắk",
        "Gia Lai",
        "Lâm Đồng",
        "Khánh Hòa",
        "Quảng Ngãi",
        "Da Nang",
        "Đồng Nai",
        "Tây Ninh",
        "Hồ Chí Minh",
        "An Giang",
        "Cà Mau",
        "Đồng Tháp",
        "Vĩnh Long",
        "Cần Thơ",
    ];

    public VietnamController(ILogger<VietnamController> logger)
    {
        _logger = logger;
    }

    public IActionResult Index()
    {
        return View();
    }

    public IActionResult Cities()
    {
        return View(CityList);
    }
}