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

    public Message(String text, List<WebElement> childElements, String timestamp) {
        this.text = text;
        this.childElements = childElements;
        this.timestamp = timestamp;
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
        return "Message{\n" +
                "\ttimestamp='" + timestamp + '\'' +
                ", \n\ttext='" + text + '\'' +
                "\n}";
    }

    public static Message getMessageFromMsgElement(WebElement msgElement){
        if (msgElement ==  null)
            return null;
        //Check if it's a system message without text
        if (!msgElement.findElements(By.cssSelector("div.message span.message-system-body")).isEmpty()) {
            System.out.println("System message");
            return null;
        }
        String text = msgElement.findElement(By.cssSelector("div.message div.bubble div.message-text span.emojitext")).getText();
        String timestamp = msgElement.findElement(By.cssSelector(".message-datetime")).getText();
        List<WebElement> children = null; //TODO: Find messages child elements such as links or emoticons

        return new Message(text, children, timestamp);
    }
}
