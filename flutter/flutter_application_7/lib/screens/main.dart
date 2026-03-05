import 'package:flutter/material.dart';

class MainScreen extends StatelessWidget {
  const MainScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: CustomScrollView(
        slivers: [
          SliverList(
            delegate: SliverChildListDelegate.fixed([
              Padding(
                padding: EdgeInsetsGeometry.all(16),
                child: AspectRatio(
                  aspectRatio: 16 / 10,
                  child: ClipRRect(
                    borderRadius: BorderRadius.circular(8),
                    child: Container(
                      decoration: BoxDecoration(
                        image: DecorationImage(
                          fit: BoxFit.cover,
                          image: NetworkImage(
                            "https://placehold.co/480x240/${_getHexColor(Colors.red[200]!)}/${_getHexColor(Colors.red[300]!)}.png",
                          ),
                        ),
                      ),
                      padding: EdgeInsets.all(16),
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          Text(
                            "Too busy to make a cake? Design your own cakes in a few minutes:",
                            style: TextStyle(fontSize: 28, height: 1.3),
                          ),
                          SizedBox(height: 12),
                          MaterialButton(
                            onPressed: () {},
                            color: Colors.red[400],
                            child: Text(
                              "Get started",
                              style: TextStyle(fontSize: 16),
                            ),
                            padding: EdgeInsets.symmetric(
                              vertical: 16,
                              horizontal: 20,
                            ),
                          ),
                        ],
                      ),
                    ),
                  ),
                ),
              ),
              Padding(
                padding: EdgeInsetsGeometry.all(16),
                child: Text(
                  "Top picks for you",
                  style: TextStyle(fontSize: 20, height: 1.3),
                ),
              ),
            ]),
          ),
          SliverPadding(
            padding: EdgeInsets.all(8),
            sliver: SliverGrid.builder(
              gridDelegate: SliverGridDelegateWithMaxCrossAxisExtent(
                maxCrossAxisExtent: 200,
                childAspectRatio: 0.8,
              ),
              itemBuilder: (context, index) {
                return Padding(
                  padding: EdgeInsetsGeometry.all(8),
                  child: Column(
                    children: [
                      ClipRRect(
                        borderRadius: BorderRadiusGeometry.circular(16),
                        child: Image.network(
                          "https://placehold.co/240x240/${_getHexColor(Colors.red[200]!)}/${_getHexColor(Colors.red[300]!)}.png",
                          fit: BoxFit.cover,
                        ),
                      ),
                      SizedBox(height: 8),
                      Text("Item #$index"),
                    ],
                  ),
                );
              },
            ),
          ),
        ],
      ),
    );
  }

  String _getHexColor(Color color) =>
      (color.toARGB32() % 0x1000000).toRadixString(16).toUpperCase();
}
