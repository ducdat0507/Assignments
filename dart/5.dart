import 'dart:io';
import 'dart:math';

Future<double> add(double a, double b) async {
  await Future.delayed(
    Duration(milliseconds: (Random().nextDouble() * 2000 + 2000).floor()),
  );
  double result = a + b;
  stdout.writeln("$a + $b = $result");
  return result;
}

Future<double> sub(double a, double b) async {
  await Future.delayed(
    Duration(milliseconds: (Random().nextDouble() * 2000 + 2000).floor()),
  );
  double result = a - b;
  stdout.writeln("$a - $b = $result");
  return result;
}

Future<double> mul(double a, double b) async {
  await Future.delayed(
    Duration(milliseconds: (Random().nextDouble() * 2000 + 2000).floor()),
  );
  double result = a * b;
  stdout.writeln("$a * $b = $result");
  return result;
}

Future<double> div(double a, double b) async {
  await Future.delayed(
    Duration(milliseconds: (Random().nextDouble() * 2000 + 2000).floor()),
  );
  double result = a / b;
  stdout.writeln("$a / $b = $result");
  return result;
}

void main() async {
  stdout.writeln("Calculating result...");
  double a = 20.0;
  double b = 5.0;
  await [add(a, b), sub(a, b), mul(a, b), div(a, b)].wait;
  stdout.writeln("All calculations complete");

  for (; a < 0;) {
    
  }
}
