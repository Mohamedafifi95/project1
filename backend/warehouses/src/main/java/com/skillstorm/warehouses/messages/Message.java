package com.skillstorm.warehouses.messages;

import java.util.Objects;

//this Message class is for setting the data response message for each HTTP request


public class Message {
    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Message{" +
                "info='" + info + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message message = (Message) o;
        return getInfo().equals(message.getInfo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInfo());
    }
}
