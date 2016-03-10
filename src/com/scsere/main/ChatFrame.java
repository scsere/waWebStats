package com.scsere.main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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

    public String getLastSeenTime(){
        return frame.findElement(By.cssSelector("div.chat-status:nth-child(2)")).getText();
    }

    public void startOnlineListener(){
        onlineListener = new OnlineListener(frame.findElement(By.cssSelector("div.chat-status:nth-child(2)")));
    }

    public void stopOnlineListener(){
        onlineListener.setActive(false);
    }

    public List<Message> getMessages(){
        //TODO: Implement
        return null;
    }

    public WebElement getFrame() {
        return frame;
    }

    public void setFrame(WebElement frame) {
        this.frame = frame;
    }
}
