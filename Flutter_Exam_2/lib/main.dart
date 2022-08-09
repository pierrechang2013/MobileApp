import 'package:http/http.dart' as http;
import 'package:flutter/material.dart';

main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        home: Scaffold(
      body: Fii(),
    ));
  }
}

class Fii extends StatefulWidget {
  const Fii({Key? key}) : super(key: key);

  @override
  _FiiState createState() => _FiiState();
}

class _FiiState extends State<Fii> {
  bool _click = false;
  late TextEditingController _controller_Couriel;
  late TextEditingController _controller_MotdePass;

  String? couriel = null;

  //print("couriel"+couriel);
  String? motDePasse = null;

  @override
  void initState() {
    //初始化方法
    super.initState();
    _controller_Couriel = TextEditingController();
    _controller_MotdePass = TextEditingController();
  }

  @override
  void dispose() {
    _controller_Couriel.dispose();
    _controller_MotdePass.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Container(
        width: 300,
        height: 300,
        child: ListView(
          children: [
            TextField(
              onSubmitted: (value) {},
              onChanged: (value) {
                couriel = value;
              },
              decoration: const InputDecoration(
                hintText: "Couriel",
              ),
            ),
            TextField(
                decoration: const InputDecoration(
                  hintText: "Mot de passe",
                ),
                onSubmitted: (value) {
                  //motDePasse = value;
                },
                onChanged: (value) {
                  motDePasse = value;

                },
                obscureText: true),
            Row(
              mainAxisAlignment: MainAxisAlignment.start,
              children: [
                const Text("Se souvenir"),
                Switch(
                    value: _click,
                    onChanged: (value) {
                      setState(() {
                        _click = value;
                      });
                    }),
              ],
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.end,
              children: [
                ElevatedButton(
                    style: ButtonStyle(
                      backgroundColor: MaterialStateProperty.all(Colors.green),
                      padding: MaterialStateProperty.all(
                          const EdgeInsets.only(right: 0)),
                      fixedSize: MaterialStateProperty.all(const Size(100, 30)),
                    ),
                    onPressed: () async {
                      if (couriel != null &&
                          couriel!.length > 0 &&
                          motDePasse != null &&
                          motDePasse!.length > 0) {
                        //print(couriel);
                        //print(motDePasse);
                        String str = await fetch();
                        print(str);
                        if (str == "Success!"&&_click==true) {
                          print("Enregistrement des infos usager en cours…");
                        } else {
                          //print(str);
                          print(".............");
                        }
                      } else {
                        print("Erreur, les champs ne peuvent pas être vides");
                      }
                    },
                    child: const Text("Connextion")),
              ],
            ),
          ],
        ),
      ),
    );
  }
}

Future<String> fetch() async {
  String str = "";
  final Future<http.Response> response =
      http.get(Uri.parse('https://httpbin.org/delay/5'));
  await response.then((value) {
    //print(value.statusCode);

    if (value.statusCode == 200) {
      str = "Success!";
    } else {
      str = "Fail!";
      //throw Exception('Server failure!');

    }
  });

  return str;
}
