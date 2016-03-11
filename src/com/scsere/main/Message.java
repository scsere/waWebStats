package com.scsere.main;

import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by scsere on 10/03/16.
 * Project: waWebStats
 */
public class Message {

    private String text;
    private List<WebElement> childElemements;
    private String timestamp;

    public Message(String text, List<WebElement> childElemements, String timestamp) {
        this.text = text;
        this.childElemements = childElemements;
        this.timestamp = timestamp;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<WebElement> getChildElemements() {
        return childElemements;
    }

    public void setChildElemements(List<WebElement> childElemements) {
        this.childElemements = childElemements;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Message{\n" +
                "\ttimestamp='" + timestamp + '\'' +
                ", \n\ttext='" + text + '\'' +
                "\n}";
    }
}
