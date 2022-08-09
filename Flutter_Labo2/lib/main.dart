import 'package:flutter/material.dart';
import 'Routes.dart';

main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        theme: ThemeData(
            primarySwatch: Colors.blue, splashColor: Colors.transparent),
        home: const HomePage());
  }
}

class HomePage extends StatefulWidget {
  const HomePage({Key? key}) : super(key: key);

  @override
  State<HomePage> createState() => _HomePageState();
}

//混入，因为不能多次继承
class _HomePageState extends State<HomePage>
    with SingleTickerProviderStateMixin {
  AnimationController? _contrllor;
  Animation? _animation;

  bool isLock = true;

  @override
  void initState() {
    super.initState();
    _contrllor = AnimationController(
        vsync: this,
        duration: const Duration(milliseconds: 500),
        lowerBound: 0.3,
        upperBound: 1);

    //对于动画，这段是必须的
    _contrllor!.addListener(() {
      setState(() {});
    });

    _contrllor!.addStatusListener((status) {
      //反复播放
      // if (status == AnimationStatus.completed) {
      //   _contrllor!.reverse();
      // } else if (status == AnimationStatus.dismissed) {
      //   _contrllor!.forward();
      // }

      //只播放一次
      if (status == AnimationStatus.completed) {
        _contrllor!.reverse();
      }
    });

    _animation = CurvedAnimation(
      parent: _contrllor!,
      curve: Curves.ease,
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text("Ma maison")),
      body: Padding(
        padding: const EdgeInsets.all(8.0),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          //mainAxisSize: MainAxisSize.min,

          children: [
            Column(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: [
                const Text(
                  "Serrure",
                  style: TextStyle(fontSize: 30),
                ),
                Padding(
                  padding: const EdgeInsets.all(20.0),
                  child: Opacity(
                    opacity: _contrllor!.value,
                    child: isLock
                        ? const Icon(Icons.lock_outlined, size: 200)
                        : const Icon(Icons.lock_open, size: 200),
                  ),
                ),
              ],
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              //mainAxisSize: MainAxisSize.min,
              children: [
                Container(
                  width: 100,
                  height: 100,
                  child: ElevatedButton(
                    child: const Icon(
                      Icons.lock_outlined,
                      size: 50,
                    ),
                    style: ButtonStyle(
                      backgroundColor:
                          MaterialStateProperty.resolveWith((states) {
                        return Colors.grey[500];
                      }),
                    ),
                    onPressed: () {
                      _contrllor!.forward();
                      isLock = true;
                      setState(() {});
                    },
                  ),
                ),
                Container(
                  width: 100,
                  height: 100,
                  child: ElevatedButton(
                    child: const Icon(
                      Icons.lock_open,
                      size: 50,
                    ),
                    style: ButtonStyle(
                      backgroundColor:
                          MaterialStateProperty.resolveWith((states) {
                        return Colors.grey[500];
                      }),
                    ),
                    onPressed: () {
                      _contrllor!.forward();

                      isLock = false;
                      setState(() {});
                    },
                  ),
                ),
                Container(
                  width: 100,
                  height: 100,
                  child: ElevatedButton(
                    child: const Icon(
                      Icons.thermostat_outlined,
                      size: 50,
                    ),
                    onPressed: () {
                      Navigator.of(context).push(createRoute());
                    },
                  ),
                )
              ],
            ),
          ],
        ),
      ),
    );
  }
}

//
