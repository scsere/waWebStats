package com.scsere.main.listeners;

import com.scsere.main.Message;

/**
 * Created by scsere on 11/03/16.
 * Project: waWebStats
 */
public interface ChatListener {

    void onStatusChanged(String newStatus);

    void onNewMessage(Message message);

    void onNewIncomingMessage(Message incomingMessage);
}
