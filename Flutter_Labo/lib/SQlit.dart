import 'dart:async';
import 'package:flutter/material.dart';
import 'package:path/path.dart';
import 'package:sqflite/sqflite.dart';
import 'package:flutter/widgets.dart';
Future getDb() async {
  WidgetsFlutterBinding.ensureInitialized();
  Database db =
      await openDatabase(join(await getDatabasesPath(), 'animals_database.db'),
          onCreate: (db, version) {
    return db.execute(
        'CREATE TABLE animals(id INTEGER PRIMARY KEY, name TEXT, age INTEGER)');
  }, version: 1);
  return db;
}
class Animal {
  final int id, age;
  final String name;
  const Animal({required this.id, required this.name, required this.age});
  Map<String, dynamic> toMap() {
    return {'id': id, 'name': name, 'age': age};
  }
  @override
  String toString() {
    return 'Animal{id: $id, name: $name, age: $age}';
  }
}
Future<int> insertAnimal(Animal animal, Database db) {
  return db.insert(
    'Animals',
    animal.toMap(),
    conflictAlgorithm: ConflictAlgorithm.replace,
  );
}
Future<List<Animal>> loadAnimals(Database db) async {
  final List<Map<String, dynamic>> maps = await db.query('animals');
  return List.generate(maps.length, (i) {
    return Animal(
      id: maps[i]['id'],
      name: maps[i]['name'],
      age: maps[i]['age'],
    );
  });
}
Future<int> deleteAnimal(int id, Database db) {

  return db.delete(
    'animals',
    where: 'id = ?',
    whereArgs: [id],
  );
}
void  deleteAll(Database db) async{
 int res = await db.delete("animals");

  print("after delete....."+res.toString());
}
Future getAllData1(Database db) async {

  WidgetsFlutterBinding.ensureInitialized();

  final List<Map<String, dynamic>> maps = await db.query('animals');

  // Convert the List<Map<String, dynamic> into a List<Dog>.
  var list = List.generate(maps.length, (i) {
    return Animal(
      id: maps[i]['id'],
      name: maps[i]['name'],
      age: maps[i]['age'],
    );
  });
  String str = "";
  print("Aninmal:${list.length}");
  for (var ll in list) {
    str = str + " " + "id:${ll.id},name:${ll.name}, age:${ll.age}.\n";
  }
  //str = "${list}";
  return list;
}
Future getAllData() async {
  WidgetsFlutterBinding.ensureInitialized();
  Database db =
  await openDatabase(join(await getDatabasesPath(), 'animals_database.db'),
      onCreate: (db, version) {
        return db.execute(
            'CREATE TABLE animals(id INTEGER PRIMARY KEY, name TEXT, age INTEGER)');
      }, version: 1);
  final List<Map<String, dynamic>> maps = await db.query('animals');
  var list = List.generate(maps.length, (i) {
    return Animal(
      id: maps[i]['id'],
      name: maps[i]['name'],
      age: maps[i]['age'],
    );
  });
  String str = "";
  str = "${list}";
  //print("${list}");
  return str;
}
