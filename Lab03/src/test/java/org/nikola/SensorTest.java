package org.nikola;
import org.junit.Test;
import org.nikola.services.Sensor;

import static org.junit.Assert.assertEquals;

public class SensorTest {
    @Test
    public void stringify_Test() {
        var expected = "{ \"sensorName\": \"-\", \"factor\": 0, \"lowerLimit\": 0, \"upperLimit\": 0, \"value\": 0, \"measurementUnit\": \"-\"}";
        var actual = new Sensor("-", "-", 0, 0, 0);
        assertEquals(actual.stringify(), expected);
    }

}
