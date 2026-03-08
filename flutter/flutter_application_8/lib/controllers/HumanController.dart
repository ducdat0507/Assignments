import 'package:flutter_application_8/models/Human.dart';

class HumanController {
  static HumanController _instance = HumanController._singletonInit();

  List<Human> _list = [
    Human(id: 1, name: 'Nguyen An', age: 22, emailAddress: 'an@gmail.com'),
    Human(id: 2, name: 'Tran Binh', age: 25, emailAddress: 'binh@gmail.com'),
    Human(id: 3, name: 'Le Chi', age: 21, emailAddress: 'chi@gmail.com'),
    Human(id: 4, name: 'Pham Dung', age: 24, emailAddress: 'dung@gmail.com'),
  ];

  factory HumanController() {
    return _instance;
  }

  HumanController._singletonInit();

  Future<List<Human>> getAll() async {
    await Future.delayed(Duration(seconds: 1));
    return _list;
  }
}
