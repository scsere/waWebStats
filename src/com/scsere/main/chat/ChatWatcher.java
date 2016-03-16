package com.scsere.main.chat;

import com.scsere.main.Watcher;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by scsere on 10/03/16.
 * Project: waWebStats
 */
public class ChatWatcher extends Watcher<ChatFrame, ChatListener> {

    private String lastState = "";
    private Message lastMessage = null;

    public ChatWatcher(ChatFrame parent) {
        super(parent);
    }

    @Override
    protected void performChecks(List<ChatListener> listeners) {
        try {
            checkMessages();
            checkStatus();
        } catch (StaleElementReferenceException e) {
            setActive(false);
            for (ChatListener listener : parent.getListeners())
                listener.onChatNotAvailable();
            System.err.println("Lost chatFrame reference (changed chat?)");
        }
    }

    private void checkMessages() {
        //If lastMessage is not initialized, load lastMessage
        if (lastMessage == null) {
            lastMessage = parent.getMessages().get(parent.getMessages().size() - 1);
            assert lastMessage != null;
            return;
        }
        final List<Message> messageList = parent.getMessages();
        final Message currentLastMessage = messageList.get(messageList.size() - 1);
        if (!lastMessage.equals(currentLastMessage)) {
            for (ChatListener listener : parent.getListeners()) {
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
            for (ChatListener listener : parent.getListeners())
                listener.onStatusChanged(currentText);
            lastState = currentText;
        }
    }
}
