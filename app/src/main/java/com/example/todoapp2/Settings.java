package com.example.todoapp2;

import android.graphics.Color;

public class Settings {
    int themeColor;
    enum  FONT{
        NORMAL,
        BOLD,
        ITALIC
    }

    public int getThemeColor() {
        return themeColor;
    }

    public void setThemeColor(int themeColor) {
        this.themeColor = themeColor;
    }
}
