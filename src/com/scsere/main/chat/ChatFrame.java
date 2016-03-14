package com.scsere.main.chat;

import com.scsere.main.Listenable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by scsere on 10/03/16.
 * Project: waWebStats
 */
public class ChatFrame extends Listenable<ChatListener> {
    private WebElement frame;

    public ChatFrame(WebElement frame) {
        this.frame = frame;
    }

    public String getLastSeenTime() {
        return frame.findElement(By.cssSelector("div.chat-status:nth-child(2)")).getText();
    }

    public WebElement getStatusElement() {
        List<WebElement> elements = frame.findElements(By.cssSelector("div.chat-status:nth-child(2)"));
        return elements.isEmpty() ? null : elements.get(0);
    }

    public boolean sendText(String textToSend) {
        //Find input element
        final List<WebElement> inputElements = frame.findElements(By.cssSelector("div.input"));
        if (inputElements.isEmpty())
            return false;
        inputElements.get(0).sendKeys(textToSend + "\n");
        return true;
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

    @Override
    protected void startWatcher() {
        watcher = new ChatWatcher(this);
    }

    @Override
    protected void stopWatcher() {
        watcher.setActive(false);
        watcher = null;
    }
}
