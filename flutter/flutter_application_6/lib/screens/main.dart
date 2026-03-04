import 'package:flutter/material.dart';
import 'package:flutter_application_6/data/data.dart';
import 'package:flutter_application_6/models/food.dart';
import 'package:flutter_application_6/models/food_category.dart';
import 'package:flutter_application_6/screens/food_detail.dart';

class MainScreen extends StatefulWidget {
  const MainScreen({super.key});

  @override
  State<MainScreen> createState() => _MainScreenState();
}

class _MainScreenState extends State<MainScreen> {
  List<FoodCategory> foodCategories = [];
  List<Food> foods = [];

  @override
  void initState() {
    var repo = Repository();
    foodCategories = repo.getFoodCategories().toList();
    foods = repo.getFoods().toList();
  }

  @override
  Widget build(BuildContext context) {
    return CustomScrollView(
      slivers: [
        SliverPadding(
          padding: EdgeInsets.all(16),
          sliver: SliverList(
            delegate: SliverChildListDelegate.fixed([
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Text("Hello, Mark Adam"),
                  IconButton.outlined(
                    onPressed: () {},
                    icon: Icon(Icons.person),
                  ),
                ],
              ),
              Padding(
                padding: EdgeInsets.only(right: 100),
                child: Text(
                  "Enjoy Tasty Food in Your Town",
                  style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
                ),
              ),
              SizedBox(height: 12),
              SearchBar(
                hintText: "Find Your Meal",
                elevation: WidgetStatePropertyAll(0),
                padding: WidgetStatePropertyAll(
                  EdgeInsets.only(left: 16, right: 8, top: 8, bottom: 8),
                ),
                leading: Icon(Icons.search),
                shape: WidgetStatePropertyAll(
                  RoundedRectangleBorder(
                    side: BorderSide(color: Colors.black, width: 1),
                    borderRadius: BorderRadius.all(Radius.circular(8)),
                  ),
                ),
                backgroundColor: WidgetStatePropertyAll(Colors.white),
              ),
              SizedBox(height: 12),
              Text(
                "Category",
                style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
              ),
              SizedBox(height: 12),
              SizedBox(
                height: 40,
                child: ListView.separated(
                  shrinkWrap: true,
                  scrollDirection: Axis.horizontal,
                  itemCount: foodCategories.length,
                  itemBuilder: (context, index) =>
                      _buildFoodCategoryCard(foodCategories[index]),
                  separatorBuilder: (context, index) => SizedBox(width: 12),
                ),
              ),
            ]),
          ),
        ),
        SliverPadding(
          padding: EdgeInsets.all(8),
          sliver: SliverGrid(
            delegate: SliverChildBuilderDelegate(
              (context, index) => _buildFoodCard(foods[index]),
              childCount: foods.length,
            ),
            gridDelegate: SliverGridDelegateWithMaxCrossAxisExtent(
              maxCrossAxisExtent: 320,
              childAspectRatio: 3.0 / 4.0,
            ),
          ),
        ),
      ],
    );
  }

  Widget _buildFoodCategoryCard(FoodCategory data) {
    return MaterialButton(
      onPressed: () {},
      child: Text(data.name),
      shape: RoundedRectangleBorder(
        side: BorderSide(color: Colors.black, width: 1),
        borderRadius: BorderRadius.all(Radius.circular(8)),
      ),
    );
  }

  Widget _buildFoodCard(Food data) {
    return Card(
      margin: EdgeInsets.all(8),
      shape: RoundedRectangleBorder(
        side: BorderSide(color: Colors.black, width: 1),
        borderRadius: BorderRadius.all(Radius.circular(8)),
      ),
      elevation: 0,
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.stretch,
        children: [
          Padding(
            padding: EdgeInsets.all(8),
            child: Row(
              children: [
                Icon(Icons.star),
                Text(data.rating.toStringAsFixed(1)),
              ],
            ),
          ),
          Expanded(
            child: MaterialButton(
              onPressed: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(builder: (context) => FoodDetailScreen(data)),
                );
              },
              child: Image.network(
                data.thumbnailUrl, 
                fit: BoxFit.cover,
              ),
            ),
          ),
          Padding(
            padding: EdgeInsets.all(12),
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text(data.name, style: TextStyle(color: Colors.black)),
                    Text(
                      "\$${data.priceDollars.toStringAsFixed(2)}",
                      style: TextStyle(color: Colors.black, fontSize: 16),
                    ),
                  ],
                ),
                IconButton.outlined(onPressed: () {}, icon: Icon(Icons.add)),
              ],
            ),
          ),
        ],
      ),
    );
  }
}
