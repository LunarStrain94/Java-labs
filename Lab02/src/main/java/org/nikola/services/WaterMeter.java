package org.nikola.services;
import lombok.Getter;

@Getter
public class WaterMeter {
    private Sensor meter[];
    public WaterMeter() {
        meter = new Sensor[4];
        meter[0] = new Sensor("CurrentWaterTemperature", "Celsius", -32668, 32668, 10);
        meter[1] = new Sensor("CurrentWaterPressure", "Bar", 0, 65336, 1000);
        meter[2] = new Sensor("WaterConsumptionRecent", "Litre", 0, 65336, 0);
        meter[3] = new Sensor("WaterConsumption", "CubicMetre", 0, 65336, 10);
    }

    public void randomizeAll() {
        for (Sensor i : this.meter) {
            i.randomize();
        }
    }

}
