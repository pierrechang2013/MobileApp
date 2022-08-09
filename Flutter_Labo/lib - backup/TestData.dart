import 'SQlit.dart' as sq;
import 'package:flutter/material.dart';
import 'package:sqflite/sqflite.dart';
import 'dart:math';
//insert data
main1(){
runApp(MyApp());

}
void main() async {
   Database db  = await sq.getDb();
   //Sq.deleteAll(db);
   //Sq.getAllData();
   for(int i = 1;i<=100; i++){
     var random = Random().nextInt(100);
     var animal = sq.Animal(id:i ,name: 'Fido'+random.toString(), age: random);
     sq.insertAnimal(animal, db);
   }

  //await insertDog(fido, db);
  //print(await loadAnimals(db));
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);



  @override
  Widget build(BuildContext context) {

    return MaterialApp(
        home: Center(
          child: FutureBuilder(
            future: sq.getAllData(),
            builder: (context, snapshot) {
              print("snapshot.connectionState:" +
                  snapshot.connectionState.toString());
              if (snapshot.hasData) {
                // String myString = snapshot.data!.toString();
                return Text(
                  snapshot.data!.toString(),
                  style: const TextStyle(color: Colors.white, fontSize: 40),
                );
              } else if (snapshot.hasError) {
                return Text('${snapshot.error}');
              }

              return const CircularProgressIndicator();
            },
          ),
        ));
  }
}