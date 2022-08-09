import 'package:shared_preferences/shared_preferences.dart';
import 'package:flutter_keychain/flutter_keychain.dart';

void insertSharedPreferences(String email) async {
  final prefs = await SharedPreferences.getInstance();

  prefs.setString("email", email);
  //prefs.setString("motDePasse", pwd);

}

void saveInKeyChain( String pwd) async {
  //await FlutterKeychain.put(key: "email", value: email);
  await FlutterKeychain.put(key: "motDePasse", value: pwd);


}
