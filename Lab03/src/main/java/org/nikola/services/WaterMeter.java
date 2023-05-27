package org.nikola.services;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Getter
public class WaterMeter {
    private Sensor meter[];
    private String broker;
    public WaterMeter() {
        meter = new Sensor[4];
        meter[0] = new Sensor("CurrentWaterTemperature", "Celsius", -32668, 32668, 10);
        meter[1] = new Sensor("CurrentWaterPressure", "Bar", 0, 65336, 1000);
        meter[2] = new Sensor("WaterConsumptionRecent", "Litre", 0, 65336, 0);
        meter[3] = new Sensor("WaterConsumption", "CubicMetre", 0, 65336, 10);
        broker = "mosquitto";
    }

    public WaterMeter(Sensor waterMeter[], String brok) {
        meter = waterMeter;
        broker = brok;
    }

    public void randomizeAll() {
        for (Sensor i : this.meter) {
            i.randomize();
        }
    }
    public static void publishMessage(String URI, String topic, String msg) {
        String publisherId = UUID.randomUUID().toString();
        try {
            var publisher = new MqttClient(URI, publisherId);
            publisher.connect();
            var message = new MqttMessage(msg.getBytes());
            message.setQos(0);
            publisher.publish(topic, message);
            publisher.disconnect();
        }
        catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void serialize(String filepath) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(filepath), this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static WaterMeter deserialize(String pathname) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            WaterMeter m = mapper.readValue(new File(pathname), WaterMeter.class);
            return m;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
