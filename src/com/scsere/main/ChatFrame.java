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
    private ChatWatcher chatWatcher;

    public ChatFrame(WebElement frame) {
        this.frame = frame;
    }

    public String getLastSeenTime() {
        return frame.findElement(By.cssSelector("div.chat-status:nth-child(2)")).getText();
    }

    public void startOnlineListener(){
        chatWatcher = new ChatWatcher(frame.findElement(By.cssSelector("div.chat-status:nth-child(2)")));
    }

    public void stopOnlineListener(){
        chatWatcher.setActive(false);
    }

    public List<Message> getMessages() {
        List<Message> messageList = new ArrayList<>();
        WebElement messageListElement = frame.findElement(By.cssSelector(".message-list"));
        for (WebElement singleMessage : messageListElement.findElements(By.className("msg"))) {
            Message msg = Message.getMessageFromMsgElement(singleMessage);
            if (msg != null)
                messageList.add(msg);
        }
        return messageList.size() > 0 ? messageList : null;
    }

    public WebElement getFrame() {
        return frame;
    }

    public void setFrame(WebElement frame) {
        this.frame = frame;
    }
}
