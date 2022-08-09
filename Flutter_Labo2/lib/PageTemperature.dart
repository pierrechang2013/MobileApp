import 'package:flutter/material.dart';
import 'ConversionDeTemp.dart';
class Temp extends StatefulWidget {
    Temp({Key? key}) : super(key: key);

  @override
  State<Temp> createState() => _TempState();
}
enum tempType{isC,isF,isK}

class _TempState extends State<Temp> {

  var temp = "22.0\u00B0C";
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Température"),
      ),
      body: Column(
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,

        children: [
          Center(
            child: Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Padding(
                //alignment: Alignment.center,
                padding: const EdgeInsets.all(100),
                child: Text(temp,
                  textAlign: TextAlign.center,
                  style: const TextStyle(fontSize: 60),
                ),

              ),]
            ),
          ),
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: [
              Container(
                width: 100,
                height: 100,
                child: ElevatedButton(
                    onPressed: () {

                      temp = getTemp(tempType.isC, 22.0);
                      setState(() {

                      });
                    },
                    style: ButtonStyle(
                      backgroundColor:
                          MaterialStateProperty.resolveWith((states) {
                        return Colors.grey[500];
                      }),
                    ),
                    child: const Text(
                      "C",
                      style: TextStyle(
                          fontSize: 40,
                          //backgroundColor: Colors.grey,
                          color: Colors.white),
                    )),
              ),
              Container(
                width: 100,
                height: 100,
                child: ElevatedButton(
                    onPressed: () {
                      temp = getTemp(tempType.isF, 22.0);
                      setState(() {

                      });

                    },
                    style: ButtonStyle(
                      backgroundColor:
                          MaterialStateProperty.resolveWith((states) {
                        return Colors.grey[500];
                      }),
                    ),
                    child: const Text(
                      "F",
                      style: TextStyle(
                          fontSize: 40,
                          //backgroundColor: Colors.grey,
                          color: Colors.white),
                    )),
              ),
              Container(
                width: 100,
                height: 100,
                child: ElevatedButton(
                    style: ButtonStyle(
                      backgroundColor:
                          MaterialStateProperty.resolveWith((states) {
                        return Colors.grey[500];
                      }),
                    ),
                    onPressed: () {
                      temp = getTemp(tempType.isK, 22.0);
                      setState(() {

                      });
                    },
                    child: const Text(
                      "K",
                      style: TextStyle(
                          fontSize: 40,
                          //backgroundColor: Colors.grey,
                          color: Colors.white),
                    )),
              )
            ],
          )
        ],
      ),
    );
  }
}

