import 'package:flutter/material.dart';
import 'package:flutter_application_6/screens/main.dart';

void main() {
  runApp(const MainApp());
}

class MainApp extends StatelessWidget {
  const MainApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        body: Navigator(
          pages: [
            MaterialPage(child: MainScreen())
          ],
          onDidRemovePage: (page) {},
        ),
        bottomNavigationBar: BottomAppBar(
          height: 60,
          padding: EdgeInsets.all(0),
          color: Colors.black,
          child: Row(
            crossAxisAlignment: CrossAxisAlignment.stretch,
            children: [
              Expanded(
                child: 
                MaterialButton(
                  onPressed: () {},
                  child: Icon(Icons.home_outlined, color: Colors.white),
                )
              ),
              Expanded(
                child: 
                MaterialButton(
                  onPressed: () {},
                  child: Icon(Icons.favorite_outline_rounded, color: Colors.white),
                )
              ),
              Expanded(
                child: 
                MaterialButton(
                  onPressed: () {},
                  child: Icon(Icons.shopping_cart_outlined, color: Colors.white),
                )
              ),
              Expanded(
                child: 
                MaterialButton(
                  onPressed: () {},
                  child: Icon(Icons.person_outline_rounded, color: Colors.white),
                )
              ),
            ],
          ),
        ),
      ),
    );
  }
}
