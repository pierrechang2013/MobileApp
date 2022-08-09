import 'package:flutter/material.dart';
import 'PageTemperature.dart';


Route createRoute() {

  return PageRouteBuilder(
      transitionDuration: const Duration(seconds: 1),
      pageBuilder: (context, animation, secondaryAnimation) => Temp(),
      transitionsBuilder:(context, animation, secondaryAnimation, child) {
         var begin = const Offset(1.0, 0.0);
         var end = const Offset(0.0, 0.0);

        var tween = Tween(begin: begin, end: end).chain(CurveTween(curve: Curves.ease));

        return SlideTransition(
          position: animation.drive(tween),
          child: child,
        );
      }
  );
}