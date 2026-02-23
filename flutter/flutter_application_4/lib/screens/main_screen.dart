import 'package:flutter/material.dart';

class MainScreenWidget extends StatelessWidget {
  const MainScreenWidget({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Scrollable(
        viewportBuilder: (context, position) => (Padding(
          padding: EdgeInsets.all(16),
          child: GridView.count(
            crossAxisCount: 2,
            crossAxisSpacing: 8,
            mainAxisSpacing: 8,
            childAspectRatio: 3 / 2.0,
            scrollDirection: Axis.vertical,
            physics: const BouncingScrollPhysics(
              parent: AlwaysScrollableScrollPhysics(),
            ),
            children: List.generate(
              16,
              (index) => (GridTile(
                child: Card(child: Center(child: Text("Card #${index + 1}"))),
              )),
            ),
          ),
        )),
      ),
    );
  }
}
