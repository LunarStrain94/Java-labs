package org.nikola;
import org.nikola.services.Sensor;
import org.nikola.services.WaterMeter;

public class Main {
    public static void main(String[] args) {
        //var m = new WaterMeter();
        //m.serialize("target/meter.json");

        var m = WaterMeter.deserialize("target/meter.json");

        for (Sensor i : m.getMeter()) {
            WaterMeter.publishMessage("tcp://localhost:1883", "Lab02:", i.stringify());
        }
        WaterMeter.publishMessage("tcp://localhost:1883", "Lab02:", "broker: \" " + m.getBroker() + "\"");
    }
}