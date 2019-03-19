package com.example.todoapp2;

import java.io.Serializable;

public class Task implements Serializable {

    String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
