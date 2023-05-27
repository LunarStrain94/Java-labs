package com.oss.lecture1;
import java.awt.Color;
import com.oss.lecture1.services.MyColor;

public class ColorConverter {
    public static void main(String[] args) {
        String hexColor = "0x1FF0FF";

        MyColor c = MyColor.decode(hexColor);

        float[] hsbCode = new float[3];
        float[] hslCode = new float[3];
        int[] cmykCode = new int[4];

        MyColor.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), hsbCode);
        MyColor.RGBtoHSL(c.getRGB(), hslCode);
        MyColor.RGBtoCMYK(c.getRGB(), cmykCode);


        System.out.println("Boja u HEX formatu: 0x" +
                Integer.toHexString(c.getRGB() & 0x00FFFFFF));
        System.out.println("Boja u RGB formatu: " + c.getRed() + ", " +
                c.getGreen() + ", " + c.getBlue());
        System.out.println("Boja u HSB formatu: " + hsbCode[0] * 360 + "°, " +
                hsbCode[1] * 100 + "%, " + hsbCode[2] * 100 + "%");
        System.out.println("Boja u HSL formatu: " + hslCode[0] + "° " +
                hslCode[1] * 100 + "% " + hslCode[2] * 100 + "%");
        System.out.println("Boja u CMYK formatu: " + cmykCode[0] + " " + cmykCode[1] + " " + cmykCode[2] + " " + cmykCode[3]);
    }
}
