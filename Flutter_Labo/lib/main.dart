import 'package:flutter/material.dart';
import 'ButtonsSkipAndConnexion.dart';
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
                        setState(() {

                        });
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
                      onChanged: (value) {
                        motDePasse = value;
                        setState(() {

                        });
                      },
                      obscureText: true),
                ),
              ],
            ),
          ),
          ButtonsSkipAndConnexion(couriel: couriel, motDePasse: motDePasse),
        ],
      ),
    );
  }
}


