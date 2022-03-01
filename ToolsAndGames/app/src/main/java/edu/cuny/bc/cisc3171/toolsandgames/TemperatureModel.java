package edu.cuny.bc.cisc3171.toolsandgames;

public class TemperatureModel {
    public final static int FAHRENHEIT = 1;
    public final static int CELSIUS = 2;
    public final static int KELVIN = 3;

    public static double convert(double fromTemperature, int fromUnit, int toUnit) {
        double toTemperature = 0;
        switch (fromUnit) {
            case FAHRENHEIT:
                switch (toUnit) {
                    case FAHRENHEIT:
                        toTemperature = fromTemperature;
                        break;
                    case CELSIUS:
                        toTemperature = convertFahrenheitToCelsius(fromTemperature);
                        break;
                    case KELVIN:
                        toTemperature = convertFahrenheitToKelvin(fromTemperature);
                        break;
                }
                break;
            default:
                throw new IllegalArgumentException(String.format("From unit %d not yet supported", fromUnit));

        }
        return toTemperature;
    }

    public static double convertFahrenheitToCelsius(double from) {
        return (from - 32.) / 1.8;
    }

    public static double convertFahrenheitToKelvin (double from) {
        return convertFahrenheitToCelsius(from) + 273.15;
    }

    // ...

}
