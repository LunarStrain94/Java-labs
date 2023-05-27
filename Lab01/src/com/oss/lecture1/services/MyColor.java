package com.oss.lecture1.services;
import java.awt.*;

import static sun.swing.MenuItemLayoutHelper.max;

public class MyColor {
    private int red, green, blue, rgb;

    public MyColor() {
        this.red = 255;
        this.green = 255;
        this.blue = 255;
        this.rgb = 0xffffff;
    }
    public MyColor(int rgb) {
        this.rgb = rgb & 0xffffff;
        this.red = (rgb & 0xff0000) >> 16;
        this.green = (rgb & 0xff00) >> 8;
        this.blue = rgb & 0xff;
    }
    public MyColor(int red, int green, int blue) {
        this.red = red & 255;
        this.green = green & 255;
        this.blue = blue & 255;
        this.rgb = (red << 16) | (green << 8) | blue;
    }

    public int getRGB() {
        return this.rgb;
    }
    public int getRed() { return this.red; }
    public int getGreen() { return this.green; }
    public int getBlue() {
        return this.blue;
    }
    public static int getRed(int rgb) { return (rgb & 0xff0000) >> 16; }
    public static int getGreen(int rgb) { return (rgb & 0xff00) >> 8; }
    public static int getBlue(int rgb) {
        return (rgb & 0xff);
    }

    public static MyColor decode(String nm) throws NumberFormatException {
        Integer intval = Integer.decode(nm);
        int i = intval.intValue();
        return new MyColor((i >> 16) & 0xFF, (i >> 8) & 0xFF, i & 0xFF);
    }

    public static float[] RGBtoHSB(int r, int g, int b, float[] hsbvals) {
        float hue, saturation, brightness;
        if (hsbvals == null) {
            hsbvals = new float[3];
        }
        int cmax = (r > g) ? r : g;
        if (b > cmax) cmax = b;
        int cmin = (r < g) ? r : g;
        if (b < cmin) cmin = b;

        brightness = ((float) cmax) / 255.0f;
        if (cmax != 0)
            saturation = ((float) (cmax - cmin)) / ((float) cmax);
        else
            saturation = 0;
        if (saturation == 0)
            hue = 0;
        else {
            float redc = ((float) (cmax - r)) / ((float) (cmax - cmin));
            float greenc = ((float) (cmax - g)) / ((float) (cmax - cmin));
            float bluec = ((float) (cmax - b)) / ((float) (cmax - cmin));
            if (r == cmax)
                hue = bluec - greenc;
            else if (g == cmax)
                hue = 2.0f + redc - bluec;
            else
                hue = 4.0f + greenc - redc;
            hue = hue / 6.0f;
            if (hue < 0)
                hue = hue + 1.0f;
        }
        hsbvals[0] = hue;
        hsbvals[1] = saturation;
        hsbvals[2] = brightness;
        return hsbvals;
    }

    public static void RGBtoHSL(int rgb, float[] hsl) {
        float r = getRed(rgb) / 255.0f;
        float g = getGreen(rgb) / 255.0f;
        float b = getBlue(rgb) / 255.0f;
        float max = Math.max(Math.max(r, g), b);
        float min = Math.min(Math.min(r, g), b);
        float c = max - min;

        float h_ = 0.f;
        if (c == 0) {
            h_ = 0;
        }
        else if (max == r) {
            h_ = (float)(g-b) / c;
            if (h_ < 0) {
                h_ += 6.f;
            }
        }
        else if (max == g) {
            h_ = (float)(b-r) / c + 2.f;
        }
        else if (max == b) {
            h_ = (float)(r-g) / c + 4.f;
        }

        float h = 60.f * h_;
        float l = (max + min) * 0.5f;
        float s;
        if (c == 0) {
            s = 0.f;
        }
        else {
            s = c / (1 - Math.abs(2.f * l - 1.f));
        }

        hsl[0] = h;
        hsl[1] = s;
        hsl[2] = l;
    }

    public static void RGBtoCMYK(int rgb1, int[] cmyk) {
        float red = (float) (getRed(rgb1)) / 255.0f * 100;
        float green = (float) (getGreen(rgb1)) / 255.0f * 100;
        float blue = (float) (getBlue(rgb1)) / 255.0f * 100;

        float black = 100.0f - Math.max(red, Math.max(green, blue));
        float cyan = (100.0f - red - black) / (100.0f - black) * 100.0f;
        float magenta = (100.0f - green - black) / (100.0f - black) * 100.0f;
        float yellow = (100.0f - blue - black) / (100.0f - black) * 100.0f;

        cmyk[0] = (int) (cyan + 0.5f);
        cmyk[1] = (int) (magenta + 0.5f);
        cmyk[2] = (int) (yellow + 0.5f);
        cmyk[3] = (int) (black + 0.5f);
    }
}
