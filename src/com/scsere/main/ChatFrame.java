package com.scsere.main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by scsere on 10/03/16.
 * Project: waWebStats
 */
public class ChatFrame {
    private WebElement frame;
    private OnlineListener onlineListener;

    public ChatFrame(WebElement frame) {
        this.frame = frame;
    }

    public String getLastSeenTime() {
        return frame.findElement(By.cssSelector("div.chat-status:nth-child(2)")).getText();
    }

    public void startOnlineListener() {
        onlineListener = new OnlineListener(frame.findElement(By.cssSelector("div.chat-status:nth-child(2)")));
    }

    public void stopOnlineListener() {
        onlineListener.setActive(false);
    }

    public List<Message> getMessages() {
        List<Message> messageList = new ArrayList<>();
        WebElement messageListElement = frame.findElement(By.cssSelector(".message-list"));
        for (WebElement singleMessage : messageListElement.findElements(By.className("msg")))
            messageList.add(Message.getMessageFromMsgElement(singleMessage));
        return messageList;
    }

    public WebElement getFrame() {
        return frame;
    }

    public void setFrame(WebElement frame) {
        this.frame = frame;
    }
}
