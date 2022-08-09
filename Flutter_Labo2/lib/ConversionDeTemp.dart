/*
*
* 1 Celsius=274.15  Kelvin

0 °C=(1.8×0+32)°F=32 °F
* */
import 'PageTemperature.dart';

double getFahrenheit(double cel) {
  return 1.8 * cel + 32;
}

double getKelvin(double cel) {
  return 274.15 + cel;
}

String getTemp(tempType ty, double cel) {
  switch (ty) {
    case tempType.isF:
      {
        print("clique F");
        return getFahrenheit(cel).toString()+"\u00B0F";
      }

    case tempType.isK:
      {
        print("clique K");
        return getKelvin(cel).toString()+"K";
      }
    default:
      {
        print("default...........C");
        return"22.0\u00B0C";
      }
  }
}
