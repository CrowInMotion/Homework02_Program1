package com.example.homework02_program1;

import android.graphics.drawable.Drawable;

public class SavedColor
{
    private Integer red;
    private Integer green;
    private Integer blue;
    private Integer colorNum;
    private String hexColorStr;
    private Drawable color;


    public SavedColor (Integer r, Integer g, Integer b, Integer cn, String hc, Drawable c)
    {
        red = r;
        green = g;
        blue = b;
        colorNum = cn;
        hexColorStr = hc;
        color = c;
    }

    //Getters and Setters
    public Integer getRed() {
        return red;
    }

    public void setRed(Integer red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(Integer green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(Integer blue) {
        this.blue = blue;
    }
    public int getColorNum() {
        return colorNum;
    }

    public void setColorNum(Integer colorNum) {
        this.blue = colorNum;
    }

    public String getHexColorStr() {
        return hexColorStr;
    }

    public void setHexColorStr(String hexColor) {
        this.hexColorStr = hexColor;
    }

    public Drawable getColor() {
        return color;
    }

    public void setColor(Drawable color) {
        this.color = color;
    }
}
