import 'package:flutter/material.dart';
import 'package:tp3/DeluorantList.dart' as dt;
import 'SharedEtKeyChain.dart' as sec;

class ButtonsSkipAndConnexion extends StatelessWidget {
  const ButtonsSkipAndConnexion({
    Key? key,
    required this.couriel,
    required this.motDePasse,
  }) : super(key: key);

  final String? couriel;
  final String? motDePasse;

  @override
  Widget build(BuildContext context) {
    return Row(
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
              //
              // print("…couriel$couriel");
              // print("…motDePasse$motDePasse");
              if (couriel != null &&
                  couriel!.length > 0 &&
                  motDePasse!= null &&
                  motDePasse!.length > 0) {

                //sav
                sec.insertSharedPreferences(couriel!);
                sec.saveInKeyChain(motDePasse!);
                print("Enregistrement des infos usager en cours…");
              } else {
                print("Erreur, les champs ne peuvent pas être vides");
              }
            },
            child: const Text("Connextion")),
      ],
    );
  }
}