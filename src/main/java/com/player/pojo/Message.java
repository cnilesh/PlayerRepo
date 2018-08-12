package com.player.pojo;

public class Message {
    private int counter;

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;
    private String type;

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "counter=" + counter +
                ", message=" + message +
                ", type='" + type + '\'' +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
