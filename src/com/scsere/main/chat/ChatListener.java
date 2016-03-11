package com.scsere.main.chat;

/**
 * Created by scsere on 11/03/16.
 * Project: waWebStats
 */
public interface ChatListener {

    void onStatusChanged(String newStatus);

    void onNewMessage(Message message);

    void onNewIncomingMessage(Message incomingMessage);
}
