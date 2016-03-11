package com.scsere.main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by scsere on 10/03/16.
 * Project: waWebStats
 */
public class Message {

    private String text;
    private List<WebElement> childElements;
    private String timestamp;
    private MessageType type;

    public Message(String text, List<WebElement> childElements, String timestamp, MessageType type) {
        this.text = text;
        this.childElements = childElements;
        this.timestamp = timestamp;
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<WebElement> getChildElements() {
        return childElements;
    }

    public void setChildElements(List<WebElement> childElements) {
        this.childElements = childElements;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "\nMessage{\n" +
                "\ttimestamp='" + timestamp + '\'' +
                ", \n\ttext='" + text + '\'' +
                ", \n\ttype='" + type + '\'' +
                "}";
    }

    public static Message getMessageFromMsgElement(WebElement msgElement) {
        if (msgElement == null)
            return null;
        //Check if it's a system message without text
        if (!msgElement.findElements(By.cssSelector("div.message span.message-system-body")).isEmpty()) {
            final String systemText = msgElement.findElement(By.cssSelector("span.hidden-token span.emojitext")).getText();
            return new Message(systemText, null, "", MessageType.SYSTEM);
        }

        //Get message text, timestamp and child elements
        String text = msgElement.findElement(By.cssSelector("div.message div.bubble div.message-text span.emojitext")).getText();
        String timestamp = msgElement.findElement(By.cssSelector(".message-datetime")).getText();
        List<WebElement> children = null; //TODO: Find messages child elements such as links or emoticons

        //Determine if message is incoming or outgoing
        if (!msgElement.findElements(By.cssSelector("div.message-out")).isEmpty())
            return new Message(text, children, timestamp, MessageType.OUT);
        else
            return new Message(text, children, timestamp, MessageType.IN);
    }

    public enum MessageType {
        IN,
        OUT,
        SYSTEM
    }
}
