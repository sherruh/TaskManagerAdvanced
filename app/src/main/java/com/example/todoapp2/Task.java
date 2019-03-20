package com.example.todoapp2;

import java.io.Serializable;
import java.util.Random;

public class Task implements Serializable {

    String title;
    String description;
    int taskId;

    Status STATUS;
    enum Status{
        CRITICAL,
        MAJOR,
        MINOR,
        DELETED,
        ALL
    }

    public Task() {
        STATUS=Status.MAJOR;
        taskId=new Random().nextInt(99999999);
    }

    public int getTaskId() {
        return taskId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public void setTitle(String title) {
        this.title = title;
    }
}
