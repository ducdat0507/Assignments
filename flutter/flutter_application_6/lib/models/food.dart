class Food {
  String id;
  String name;
  String thumbnailUrl;
  String description;
  String location;
  double priceDollars;
  double rating;
  double distanceKm;
  double preparationTimeMinutes;

  Food({
    required this.id,
    required this.name,
    required this.thumbnailUrl,
    required this.location,
    required this.description,
    required this.priceDollars,
    required this.rating,
    required this.distanceKm,
    required this.preparationTimeMinutes,
  });
}
