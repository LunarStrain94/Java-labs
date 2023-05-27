package com.oss.lecture1.services;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class MyColor_Test {
    @Test
    public void decode_Test() {
        var actualColor = MyColor.decode("0x1FF0FF");
        assertEquals(actualColor.getRed(), 31);
        assertEquals(actualColor.getGreen(), 240);
        assertEquals(actualColor.getBlue(), 255);
    }

    @Test
    public void RGBtoHSB_Test() {
        var expectedHSB = new float[3];
        expectedHSB[0] = 0.5111607f;
        expectedHSB[1] = 0.8784314f;
        expectedHSB[2] = 1.0f;

        var actualHSB = new float[3];
        MyColor.RGBtoHSB(31, 240, 255, actualHSB);

        assertEquals(actualHSB[0], expectedHSB[0], 0.0001f);
        assertEquals(actualHSB[1], expectedHSB[1], 0.0001f);
        assertEquals(actualHSB[2], expectedHSB[2], 0.0001f);
    }

    @Test
    public void RGBtoHSL_Test() {
        var expectedHSL = new float[3];
        var actualHSL = new float[3];
        expectedHSL[0] = 184.01785f;
        expectedHSL[1] = 1.00000015f;
        expectedHSL[2] = 0.56078434f;

        MyColor.RGBtoHSL(0x1FF0FF, actualHSL);
        assertEquals(actualHSL[0], expectedHSL[0], 0.0001f);
        assertEquals(actualHSL[1], expectedHSL[1], 0.0001f);
        assertEquals(actualHSL[2], expectedHSL[2], 0.0001f);
    }

    @Test
    public void RGBtoCMYK_Test() {
        var expectedCMYK = new int[4];
        var actualCMYK = new int[4];
        expectedCMYK[0] = 88;
        expectedCMYK[1] = 6;
        expectedCMYK[2] = 0;
        expectedCMYK[3] = 0;

        MyColor.RGBtoCMYK(0x1FF0FF, actualCMYK);
        assertEquals(actualCMYK[0], expectedCMYK[0]);
        assertEquals(actualCMYK[1], expectedCMYK[1]);
        assertEquals(actualCMYK[2], expectedCMYK[2]);
        assertEquals(actualCMYK[3], expectedCMYK[3]);
    }
}
