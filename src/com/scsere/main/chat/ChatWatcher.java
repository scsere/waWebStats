package com.scsere.main.chat;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by scsere on 10/03/16.
 * Project: waWebStats
 */
public class ChatWatcher extends Thread {

    public final int DEFAULT_INTERVAL = 500;

    private ChatFrame parent;
    private int interval = DEFAULT_INTERVAL;
    private volatile boolean active = true;

    private String lastState;
    private Message lastMessage = null;

    public ChatWatcher(ChatFrame parent) {
        super();
        this.parent = parent;

        //Init "last" fields
        lastState = "";
        lastMessage = parent.getMessages().get(parent.getMessages().size() - 1);

        assert lastMessage != null;

        this.setDaemon(true);
        this.start();
    }

    @Override
    public void run() {
        while (active) {
            try {

                checkStatus();
                checkMessages();

                try {
                    Thread.sleep(interval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (StaleElementReferenceException e) {
                setActive(false);
                for (ChatListener listener : parent.listeners)
                    listener.onChatNotAvailable();
                System.err.println("Lost chatFrame reference (changed chat?)");
            }
        }
    }

    private void checkMessages() {
        final List<Message> messageList = parent.getMessages();
        final Message currentLastMessage = messageList.get(messageList.size() - 1);
        if (!lastMessage.equals(currentLastMessage)) {
            for (ChatListener listener : parent.listeners) {
                listener.onNewMessage(currentLastMessage);
                if (currentLastMessage.getType() == Message.MessageType.IN)
                    listener.onNewIncomingMessage(currentLastMessage);
            }
            lastMessage = currentLastMessage;
        }
    }

    private void checkStatus() {
        final WebElement statusElement = parent.getStatusElement();
        //In case status element disappeared
        if (statusElement == null) {
            lastState = null;
            return;
        }


        String currentText = statusElement.getText();
        if (!currentText.equals(lastState)) {
            for (ChatListener listener : parent.listeners)
                listener.onStatusChanged(currentText);
            lastState = currentText;
        }
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public String getLastState() {
        return lastState;
    }
}
