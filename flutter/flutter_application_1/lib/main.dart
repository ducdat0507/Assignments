import "package:flutter/material.dart";

void main() {
  runApp(const MainApp());
}

class MainApp extends StatelessWidget {
  const MainApp({super.key});

  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      home: Scaffold(body: Center(child: CourseDetailCard())),
    );
  }
}

class CourseDetailCard extends StatelessWidget {
  const CourseDetailCard({super.key});

  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.stretch,
      children: [
        Padding(
          padding: const EdgeInsets.all(16),
          child: ClipRRect(
            borderRadius: BorderRadius.circular(24),
            child: Stack(
              children: [
                Container(
                  height: 160,
                  decoration: const BoxDecoration(
                    gradient: LinearGradient(
                      colors: [Color(0xFF0F2027), Color(0xFF203A43)],
                      begin: Alignment.topLeft,
                      end: Alignment.bottomRight,
                    ),
                  ),
                ),
                Positioned(
                  left: 16,
                  top: 16,
                  child: _Chip(label: "Giáo án video"),
                ),
                Positioned(right: 16, top: 16, child: _Chip(label: "Dễ")),
                Positioned(
                  left: 16,
                  bottom: 24,
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: const [
                      Text(
                        "Giữ vững cơ bản",
                        style: TextStyle(
                          color: Colors.white,
                          fontSize: 22,
                          fontWeight: FontWeight.bold,
                        ),
                      ),
                      SizedBox(height: 4),
                      Text(
                        "Giảng viên nhiều kinh nghiệm",
                        style: TextStyle(color: Colors.white70, fontSize: 14),
                      ),
                    ],
                  ),
                ),
              ],
            ),
          ),
        ),

        Padding(
          padding: const EdgeInsets.symmetric(horizontal: 16),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              const Text(
                "Flutter cơ bản",
                style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
              ),
              const SizedBox(height: 4),
              const Text(
                "Giảng viên: Nguyễn Văn A",
                style: TextStyle(color: Colors.grey),
              ),
              const SizedBox(height: 12),

              // Price row
              Row(
                spacing: 8,
                children: [
                  const Text(
                    "499.000đ",
                    style: TextStyle(color: Colors.red, fontSize: 20),
                  ),
                  _Tag(label: "Giảm 20%"),
                  _Tag(label: "BA20"),
                  _Tag(label: "0:40"),
                ],
              ),
              const SizedBox(height: 16),

              const Text(
                "Học Flutter từ cơ bản đến xây dựng "
                "ứng dụng hoàn chỉnh.",
                style: TextStyle(fontSize: 15),
              ),
              const SizedBox(height: 24),

              const Text("Lesson", style: TextStyle(fontSize: 18)),
              Padding(
                padding: EdgeInsetsGeometry.only(left: 16),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    const _LessonItem(index: 1, title: "Giới thiệu Flutter"),
                    const _LessonItem(index: 2, title: "Widget cơ bản"),
                    const _LessonItem(index: 3, title: "Layout"),
                    const _LessonItem(index: 4, title: "Navigation"),
                  ],
                ),
              ),
            ],
          ),
        ),

        Padding(
          padding: const EdgeInsets.all(16),
          child: SizedBox(
            height: 52,
            child: ElevatedButton(
              style: ElevatedButton.styleFrom(
                backgroundColor: const Color(0xFFFF5A5F),
                foregroundColor: const Color(0xFFFFFFFF),
                shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(26),
                ),
              ),
              onPressed: () {},
              child: const Text("Đăng ký học", style: TextStyle(fontSize: 16)),
            ),
          ),
        ),
      ],
    );
  }
}

class _Chip extends StatelessWidget {
  final String label;
  const _Chip({required this.label});

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.symmetric(horizontal: 12, vertical: 6),
      decoration: BoxDecoration(
        color: Colors.redAccent,
        borderRadius: BorderRadius.circular(20),
      ),
      child: Text(
        label,
        style: const TextStyle(color: Colors.white, fontSize: 12),
      ),
    );
  }
}

class _Tag extends StatelessWidget {
  final String label;
  const _Tag({required this.label});

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.symmetric(horizontal: 10, vertical: 6),
      decoration: BoxDecoration(
        color: const Color(0xFFF0F0F0),
        borderRadius: BorderRadius.circular(12),
      ),
      child: Text(label, style: const TextStyle(fontSize: 12)),
    );
  }
}

class _LessonItem extends StatelessWidget {
  final int index;
  final String title;

  const _LessonItem({required this.index, required this.title});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 6),
      child: Text("$index. $title"),
    );
  }
}
