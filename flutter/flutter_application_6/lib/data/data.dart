import 'package:flutter_application_6/models/food.dart';
import 'package:flutter_application_6/models/food_category.dart';

class Repository {
  Iterable<FoodCategory> getFoodCategories() {
    return [
      FoodCategory(id: 'cat_001', name: 'Popular'),
      FoodCategory(id: 'cat_002', name: 'Indian'),
      FoodCategory(id: 'cat_003', name: 'Chinese'),
    ];
  }

  Iterable<Food> getFoods() {
    return [
      Food(
        id: 'item_001',
        name: 'Sandwich',
        thumbnailUrl: 'https://placehold.co/300x300.png',
        location: "Location, City",
        description: "Lorem ipsum dolor sit amet consectetur adipisicing elit. Cum eos eligendi dolores ipsum et iste autem nobis? Atque, iure! Id sapiente impedit est sed sit, aut doloribus ex alias eum.",
        priceDollars: 150.00,
        rating: 4.5,
        distanceKm: 12.0,
        preparationTimeMinutes: 30.0,
      ),
      Food(
        id: 'item_002',
        name: 'Kebab',
        location: "Location, City",
        description: "Consequuntur veniam, mollitia error iure pariatur ipsam molestias quia iste minima ut assumenda, voluptate cumque esse delectus ex? Rem nemo recusandae quis cupiditate consequuntur impedit sunt quae fuga, maiores sequi.",
        thumbnailUrl: 'https://placehold.co/300x300.png',
        priceDollars: 250.00,
        rating: 4.2,
        distanceKm: 8.0,
        preparationTimeMinutes: 40.0,
      ),
      Food(
        id: 'item_003',
        name: 'Juice',
        location: "Location, City",
        description: "Eum sed maiores porro. Fuga beatae dolor excepturi suscipit, doloribus incidunt sapiente ullam perferendis quisquam eos repellendus, illum quaerat nihil quae ab! Aspernatur neque explicabo voluptatem alias aut soluta maxime?",
        thumbnailUrl: 'https://placehold.co/300x300.png',
        priceDollars: 80.00,
        rating: 4.0,
        distanceKm: 5.0,
        preparationTimeMinutes: 15.0,
      ),
      Food(
        id: 'item_004',
        name: 'Egg Items',
        location: "Location, City",
        description: "Amet consectetur debitis voluptate deleniti vitae excepturi voluptatum sit accusamus molestias in architecto quo, quibusdam assumenda at. Obcaecati placeat voluptatum accusantium sunt repellendus inventore deserunt eius! Cupiditate rerum voluptates exercitationem.",
        thumbnailUrl: 'https://placehold.co/300x300.png',
        priceDollars: 250.00,
        rating: 3.9,
        distanceKm: 6.0,
        preparationTimeMinutes: 25.0,
      ),
    ];
  }
}