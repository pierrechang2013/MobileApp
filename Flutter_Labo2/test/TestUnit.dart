// import 'package:test/test.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:tp4/ConversionDeTemp.dart';
import 'package:tp4/main.dart';
import 'package:tp4/PageTemperature.dart';



void main(){
  group("Group for test", (){
    test("Test getFahrenheit()", (){
      final result = getFahrenheit(22.0);
      expect(result, 71.6);
    });
    test("Test getTemp()", (){
      final result = getKelvin(22.0);
      expect(result, 296.15);
    });

    test("Test getTemp()", (){
      final result = getTemp(tempType.isK,22.0);
      expect(result, "296.15K");
    });
    test("Test getTemp()", (){
      final result = getTemp(tempType.isF,22.0);
      expect(result, "71.6\u00B0F");
    });
  });
}