import 'package:flutter/material.dart';

void main() {
  runApp(const MainApp());
}

class MainApp extends StatelessWidget {
  const MainApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          backgroundColor: Colors.blueAccent,
          foregroundColor: Colors.white,
          title: const Text("Danh bạ điện thoại"),
        ),
        body: ListView(
          padding: EdgeInsets.all(16),
          children: [
            TextFormField(),
            ContactItem(
              index: 1,
              name: "Nguyễn Văn A",
              phoneNumber: "0901 234 567",
            ),
            ContactItem(
              index: 2,
              name: "Trần Thị B",
              phoneNumber: "0912 345 678",
            ),
            ContactItem(
              index: 3,
              name: "Lê Văn C",
              phoneNumber: "0987 654 321",
            ),
            ContactItem(
              index: 4,
              name: "Phạm Thị D",
              phoneNumber: "0933 112 233",
            ),
          ],
        ),
      ),
    );
  }
}

class ContactItem extends StatelessWidget {
  final int index;
  final String name;
  final String phoneNumber;

  const ContactItem({
    super.key,
    this.index = 0,
    this.name = "",
    this.phoneNumber = "",
  });

  @override
  Widget build(BuildContext context) {
    return TextButton(
      onPressed: () {},
      style: ButtonStyle(
        foregroundColor: WidgetStatePropertyAll(Colors.black),
        padding: WidgetStatePropertyAll(EdgeInsets.all(16)),
        shape: WidgetStatePropertyAll(
          RoundedRectangleBorder(
            borderRadius: BorderRadiusGeometry.circular(0),
          ),
        ),
        alignment: Alignment.centerLeft,
      ),

      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Text("$index. $name", style: TextStyle(fontWeight: FontWeight.bold)),
          Text(phoneNumber),
        ],
      ),
    );
  }
}
