import 'package:flutter/material.dart';
import 'package:flutter_application_6/models/food.dart';

class FoodDetailScreen extends StatefulWidget {
  final Food target;

  const FoodDetailScreen(this.target, {super.key});

  @override
  State<FoodDetailScreen> createState() => _FoodDetailScreenState();
}

class _FoodDetailScreenState extends State<FoodDetailScreen> {
  @override
  Widget build(BuildContext context) {
    return Stack(
      children: [
        ListView(
          padding: EdgeInsets.only(bottom: 72),
          children: [
            AspectRatio(
              aspectRatio: 4 / 3,
              child: Image.network(
                widget.target.thumbnailUrl,
                fit: BoxFit.cover,
              ),
            ),
            Padding(
              padding: EdgeInsetsGeometry.all(16),
              child: Column(
                spacing: 16,
                crossAxisAlignment: CrossAxisAlignment.stretch,
                children: [
                  Row(
                    children: [
                      Expanded(
                        child: Column(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: [
                            Text(
                              widget.target.name,
                              style: TextStyle(
                                fontSize: 24,
                                fontWeight: FontWeight.bold,
                              ),
                            ),
                            Text(
                              widget.target.location,
                              style: TextStyle(
                                fontSize: 16,
                                color: Theme.of(context).hintColor,
                              ),
                            ),
                          ],
                        ),
                      ),
                      Text(
                        "\$${widget.target.priceDollars.toStringAsFixed(2)}",
                        style: TextStyle(fontSize: 24),
                      ),
                    ],
                  ),
                  Padding(
                    padding: EdgeInsetsGeometry.only(top: 8, bottom: 4),
                    child: Row(
                      mainAxisAlignment: MainAxisAlignment.spaceAround,
                      children: [
                        _buildFoodAttributeGroup(
                          Icons.schedule,
                          "${widget.target.preparationTimeMinutes.toStringAsFixed(0)} mins",
                        ),
                        _buildFoodAttributeGroup(
                          Icons.location_on_rounded,
                          "${widget.target.distanceKm.toStringAsPrecision(2)} km",
                        ),
                        _buildFoodAttributeGroup(
                          Icons.star_outline_rounded,
                          widget.target.rating.toStringAsFixed(1),
                        ),
                      ],
                    ),
                  ),
                  Text(
                    "Description",
                    style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
                  ),
                  Text(widget.target.description, softWrap: true),
                ],
              ),
            ),
          ],
        ),
        Padding(
          padding: EdgeInsetsGeometry.all(16),
          child: Row(
            children: [
              IconButton(
                style: IconButton.styleFrom(
                  backgroundColor: Theme.of(context).scaffoldBackgroundColor,
                ),
                onPressed: () {
                  Navigator.pop(context);
                },
                icon: Icon(Icons.arrow_back),
              ),
              Expanded(child: SizedBox(height: 1)),
              IconButton(
                style: IconButton.styleFrom(
                  backgroundColor: Theme.of(context).scaffoldBackgroundColor,
                ),
                onPressed: () {},
                icon: Icon(Icons.more_vert),
              ),
            ],
          ),
        ),
        Align(
          alignment: AlignmentGeometry.bottomCenter,
          child: Padding(
            padding: EdgeInsetsGeometry.all(16),
            child: SizedBox(
              height: 40,
              child: Row(
                crossAxisAlignment: CrossAxisAlignment.stretch,
                spacing: 4,
                children: [
                  Expanded(
                    child: MaterialButton(
                      color: Colors.black,
                      textColor: Colors.white,
                      shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.all(Radius.circular(8)),
                      ),
                      onPressed: () {
                        Navigator.pop(context);
                      },
                      child: Text("Checkout"),
                    ),
                  ),
                  IconButton(
                    style: IconButton.styleFrom(
                      backgroundColor: Theme.of(context).scaffoldBackgroundColor,
                      shape: RoundedRectangleBorder(
                        side: BorderSide(color: Colors.black, width: 1),
                        borderRadius: BorderRadius.all(Radius.circular(8)),
                      )
                    ),
                    onPressed: () {},
                    icon: Icon(Icons.favorite_outline_rounded),
                  ),
                ],
              ),
            ),
          ),
        ),
      ],
    );
  }

  Widget _buildFoodAttributeGroup(IconData icon, String text) {
    return Column(
      spacing: 12,
      children: [
        CircleAvatar(
          backgroundColor: Colors.black,
          radius: 24,
          child: Icon(icon, size: 24, color: Colors.white, fill: 0),
        ),
        Text(text),
      ],
    );
  }
}
