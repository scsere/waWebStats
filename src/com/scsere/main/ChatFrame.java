package com.scsere.main;

import com.scsere.main.listeners.ChatListener;
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
    protected List<ChatListener> listeners = new ArrayList<>();

    public ChatFrame(WebElement frame) {
        this.frame = frame;
    }

    public String getLastSeenTime() {
        return frame.findElement(By.cssSelector("div.chat-status:nth-child(2)")).getText();
    }

    public WebElement getStatusElement(){
        List<WebElement> elements = frame.findElements(By.cssSelector("div.chat-status:nth-child(2)"));
        return elements.isEmpty() ? null : elements.get(0);
    }

    private void startChatWatcher() {
        chatWatcher = new ChatWatcher(this);
    }

    private void stopChatWatcher() {
        chatWatcher.setActive(false);
    }

    public void registerChatListener(ChatListener listener) {
        //Check if it's the first listener
        if (listeners.isEmpty()) {
            listeners.add(listener);
            startChatWatcher();
        }
    }

    public boolean detachChatListener(ChatListener listener) {
        //Check if listener is registered
        if (!listeners.contains(listener))
            return false;

        //Remove the listener
        listeners.remove(listener);

        //Check if it was the last attached listener
        if (listeners.isEmpty())
            stopChatWatcher();

        return true;
    }

    public boolean sendText(String textToSend){
        //Find input element
        final List<WebElement> inputElements = frame.findElements(By.cssSelector("div.input"));
        if (inputElements.isEmpty())
            return false;
        inputElements.get(0).sendKeys(textToSend+ "\n");
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
}
