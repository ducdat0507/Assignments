import 'dart:ffi';
import 'dart:io';
import 'package:sqlite3/sqlite3.dart';

interface class HasInfo {
  void printInfo() {}
}

abstract class Person implements HasInfo {
  String name;
  DateTime birthday;

  Person(this.name, this.birthday);
}

class Student extends Person {
  Long studentId;
  List<int> marks;

  Student(this.studentId, super.name, super.birthday, this.marks);

  void printInfo() {
    stdout.writeln("Student ID: $studentId");
    stdout.writeln("Name: $name");
    stdout.writeln("Birthday: $name");
    stdout.writeln("Marks: $marks");
  }
}

class StudentRepository {
  Database _database;

  StudentRepository() : _database = sqlite3.open("db") {
    _database.execute("""
      CREATE TABLE IF NOT EXISTS Students (
        studentId INTEGER PRIMARY KEY AUTOINCREMENT,
        name TEXT NOT NULL,
        birthday TEXT NOT NULL,
        marks TEXT NOT NULL,
      )
    """);
  }

  List<Student> list() {
    var db_set = _database.select("""
      SELECT (studentId, name, birthday, marks) FROM Students
    """);
    return db_set.map(_createFromDb).toList();
  }

  Student? get(int studentId) {
    var db_set = _database.select(
      """
      SELECT (studentId, name, birthday, marks) FROM Students
        WHERE studentId = ?
    """,
      [studentId],
    );
    return db_set.map(_createFromDb).firstOrNull;
  }

  void create(Student student) {
    _database.execute(
      """
      INSERT INTO Students (name, birthday, marks)
        VALUES (?, ?, ?)
    """,
      [
        student.name,
        student.birthday.toIso8601String(),
        student.marks.join(","),
      ],
    );
  }

  void update(Student student) {
    _database.execute(
      """
      UPDATE Students
        SET
          name = ?,
          birthday = ?,
          marks = ?
        WHERE studentId = ?
    """,
      [
        student.name,
        student.birthday.toIso8601String(),
        student.marks.join(","),
        student.studentId,
      ],
    );
  }

  void deleteAt(int studentId) {
    _database.execute(
      """
      DELETE FROM Students
        WHERE studentId = ?
    """,
      [studentId],
    );
  }

  void delete(Student student) {
    _database.execute(
      """
      DELETE FROM Students
        WHERE studentId = ?
    """,
      [student.studentId],
    );
  }

  Student _createFromDb(Row row) {
    return new Student(
      row["studentId"],
      row["name"],
      DateTime.parse(row["birthday"]),
      row["marks"].toString().split(",").map((x) => int.parse(x)).toList(),
    );
  }
}
