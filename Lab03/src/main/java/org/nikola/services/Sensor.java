package org.nikola.services;
import lombok.Getter;
import java.util.concurrent.ThreadLocalRandom;

@Getter
public class Sensor {
    private String sensorName;
    private String measurementUnit;
    private int value;
    private int upperLimit;
    private int lowerLimit;
    private int factor;

    public Sensor() {
        sensorName = "-";
        measurementUnit = "-";
        value = 0;
        upperLimit = 1;
        lowerLimit = 0;
        factor = 0;
    }

    public Sensor(String name, String unit, int low, int up, int fact) {
        sensorName = name;
        measurementUnit = unit;
        upperLimit = up;
        lowerLimit = low;
        factor = fact;
        value = ThreadLocalRandom.current().nextInt(lowerLimit, upperLimit + 1);
    }
    public void randomize() {
        value = ThreadLocalRandom.current().nextInt(lowerLimit, upperLimit + 1);
    }

    public String stringify() {
        return "{ \"sensorName\": \"" + this.sensorName + "\", " + "\"factor\": " + this.factor + ", \"lowerLimit\": "
                + this.lowerLimit + ", \"upperLimit\": " + this.upperLimit + ", \"value\": " + this.value
                + ", \"measurementUnit\": \"" + this.measurementUnit + "\"}";
    }
}
