package com.kmschr.brs;

public class Color extends ColorMode {

    public Color(int color) {
        super(color);
    }

    public Color(int r, int g, int b, int a) {
        this((byte) r, (byte) g, (byte) b, (byte) a);
    }

    public Color(byte r, byte g, byte b, byte a) {
         super((b & 0xFF) | ((g & 0xFF) << 8) | ((r & 0xFF) << 16) | ((a & 0xFF) << 24));
    }

    public byte r() {
        return (byte) (color >>> 16);
    }

    public byte g() {
        return (byte) (color >>> 8);
    }

    public byte b() {
        return (byte) color;
    }

    public byte a() {
        return (byte) (color >>> 24);
    }

    @Override
    public String toString() {
        return String.format("#%02x%02x%02x%02x", r(), g(), b(), a());
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Color))
            return false;
        return this.color == ((Color) o).color;
    }

}
