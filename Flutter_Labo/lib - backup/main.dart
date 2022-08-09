import 'package:flutter/material.dart';
import 'SharedEtKeyChain.dart' as sec;
import 'package:tp3/DeluorantList.dart' as dt;

main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
        home: Scaffold(
          resizeToAvoidBottomInset: false,
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
  late TextEditingController _controller_couriel;
  late TextEditingController _controller_motdePass;

  String? couriel;
  String? motDePasse;

  @override
  void initState() {
    //初始化方法
    super.initState();
    _controller_couriel = TextEditingController();
    _controller_motdePass = TextEditingController();
  }

  @override
  void dispose() {
    _controller_couriel.dispose();
    _controller_motdePass.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Center(
      child: ListView(
        children: [
          Padding(
            padding: const EdgeInsets.only(left: 40, right: 40),
            child: Column(
              children: [
                Row(
                  children:const [
                     Text(
                      "Bel-Air",
                      style:  TextStyle(fontSize: 100),
                      textAlign: TextAlign.center,
                    ),
                  ],
                ),
                Image.network("https://picsum.photos/250?image=10"),
                Padding(
                  padding: const EdgeInsets.only(left: 35, right: 35),
                  child: TextField(
                      onSubmitted: (value) {},
                      onChanged: (value) {
                        couriel = value;
                      },
                      decoration: const InputDecoration(
                        //后置图标
                        suffixIcon: Icon(Icons.email),
                        hintText: "Couriel",
                      )),
                ),
                Padding(
                  padding: const EdgeInsets.only(left: 35, right: 35),
                  child: TextField(
                      decoration: const InputDecoration(
                        suffixIcon: Icon(Icons.lock),
                        hintText: "Mot de passe",
                      ),
                      onSubmitted: (value) {
                        //motDePasse = value;
                      },
                      onChanged: (value) {
                        motDePasse = value;
                      },
                      obscureText: true),
                ),
              ],
            ),
          ),
          Row(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              ElevatedButton(
                  style: ButtonStyle(
                    backgroundColor: MaterialStateProperty.all(Colors.orange),
                    padding: MaterialStateProperty.all(
                        const EdgeInsets.only(right: 0)),
                    fixedSize: MaterialStateProperty.all(const Size(100, 30)),
                  ),
                  onPressed: () {
                    Navigator.push(context,
                        MaterialPageRoute(builder: (context) {
                      return const dt.DeluorantList();
                    }));
                  },
                  child: const Text("Skip")),
              const SizedBox(
                width: 50,
              ),
              ElevatedButton(
                  style: ButtonStyle(
                    backgroundColor: MaterialStateProperty.all(Colors.orange),
                    padding: MaterialStateProperty.all(
                        const EdgeInsets.only(right: 0)),
                    fixedSize: MaterialStateProperty.all(const Size(100, 30)),
                  ),
                  onPressed: () {
                    if (couriel != null &&
                        couriel!.length > 0 &&
                        motDePasse!= null &&
                        motDePasse!.length > 0) {
                      //保存
                      sec.insertSharedPreferences(couriel!);
                      sec.saveInKeyChain(motDePasse!);

                      print("Enregistrement des infos usager en cours…");
                    } else {
                      print("Erreur, les champs ne peuvent pas être vides");
                    }
                  },
                  child: const Text("Connextion")),
            ],
          ),
        ],
      ),
    );
  }
}
