import 'dart:io';

import 'package:p2/p2.dart' as p2;
import 'package:p2/student.dart';

void main(List<String> arguments) {
  StudentRepository repository = new StudentRepository();

  while (true) {
    stdout.writeln("Select option:");
    stdout.writeln("1. List students");
    stdout.writeln("2. Create student");
    stdout.writeln("3. Update student");
    stdout.writeln("4. Delete student");
    stdout.writeln("5. Exit");
    String option = stdin.readLineSync() ?? "";
    option = option.trim();
    switch (option) {
      case "5":
        return;
      default:
        stdout.writeln("Invalid option");
    }
  }
}
