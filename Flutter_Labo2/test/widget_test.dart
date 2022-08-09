//import 'package:flutter_driver/flutter_driver.dart';
//import 'package:test/test.dart';//要使用这个引入，否则会报错ui:dart
import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:tp4/main.dart';
import 'package:tp4/PageTemperature.dart';


void main() {

  testWidgets('Test widget of PageTemperature', (WidgetTester tester) async {
// Build the widget
    await tester.pumpWidget(MaterialApp(home:Temp()));

// click button
    await tester.tap(find.text("C"));

    await tester.tap(find.text("F"));

    await tester.tap(find.text("K"));

    await tester.pump();
    await tester.pumpAndSettle();

    final titleFinder = find.text('Température');

    final lvFinder = find.byType(Column);

    expect(titleFinder, findsOneWidget);
    expect(lvFinder, findsOneWidget);

  });

  testWidgets('Test widget of Main', (WidgetTester tester) async {
// Build the widget
    await tester.pumpWidget(const MaterialApp(home:HomePage()));

    final titleFinder = find.text('Ma maison');

    final appBarFinder = find.byType(AppBar);

    expect(titleFinder, findsOneWidget);
    expect(appBarFinder, findsOneWidget);

  });
}
