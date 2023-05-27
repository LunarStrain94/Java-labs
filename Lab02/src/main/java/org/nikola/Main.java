package org.nikola;
import org.eclipse.paho.client.mqttv3.*;
import org.nikola.services.WaterMeter;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        var m = new WaterMeter();

        publishMessage("tcp://localhost:1883", "Lab02:", m.getMeter()[0].stringify());
        publishMessage("tcp://localhost:1883", "Lab02:", m.getMeter()[1].stringify());
        publishMessage("tcp://localhost:1883", "Lab02:", m.getMeter()[2].stringify());
        publishMessage("tcp://localhost:1883", "Lab02:", m.getMeter()[3].stringify());

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
}