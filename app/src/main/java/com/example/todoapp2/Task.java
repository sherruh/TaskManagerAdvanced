package com.example.todoapp2;

import java.io.Serializable;

public class Task implements Serializable {

    String title;
    Status STATUS;
    enum Status{
        CRITICAL,
        MAJOR,
        MINOR
    }

    public Task() {
        STATUS=Status.MAJOR;
    }

    public Status getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(Status STATUS) {
        this.STATUS = STATUS;
    }

    public String getTitle() {
        return title;
    }



    public void setTitle(String title) {
        this.title = title;
    }
}
