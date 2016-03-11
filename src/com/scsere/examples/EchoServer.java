package com.scsere.examples;

import com.scsere.main.WaWebStats;
import com.scsere.main.chat.ChatFrame;
import com.scsere.main.chat.ChatListener;
import com.scsere.main.chat.Message;
import com.scsere.main.contacts.Contact;

import java.util.List;

/**
 * Created by scsere on 11/03/16.
 * Project: waWebStats
 */
public class EchoServer {
    public static void main(String[] args) {
        WaWebStats webStats = new WaWebStats();

        webStats.initFirefox();

        //Fetch all available contacts
        List<Contact> contacts = webStats.getContacts();
        //Print all contacts and read user choice
        Utils.printContacts(contacts);
        int selectedContactIndex = Utils.selectContact();

        System.out.println("Selected: " + contacts.get(selectedContactIndex).getContactName());

        //Open chat window for selected contact
        final ChatFrame chatFrame = webStats.getChatFrameForContact(contacts.get(selectedContactIndex));

        chatFrame.registerChatListener(new ChatListener() {
            @Override
            public void onStatusChanged(String newStatus) {
            }

            @Override
            public void onNewMessage(Message message) {
            }

            @Override
            public void onNewIncomingMessage(Message incomingMessage) {
                System.out.println("New incoming message: " + incomingMessage);
                chatFrame.sendText(incomingMessage.getText());
            }
        });

        while (true) ;
    }
}
