package com.scsere.examples;

import com.scsere.main.WaWebStats;
import com.scsere.main.WebApp.WebAppFrame;
import com.scsere.main.chat.ChatFrame;
import com.scsere.main.chat.ChatListener;
import com.scsere.main.chat.Message;
import com.scsere.main.contacts.Contact;

import java.util.List;
import java.util.Scanner;

/**
 * Created by scsere on 21/03/16.
 * Project: waWebStats
 * WhatsSpp command line interface
 */
public class WACli {
    public static void main(String[] args) {
        WaWebStats api = new WaWebStats();

        api.initFirefox();

        final WebAppFrame appFrame = api.getWebAppFrame();

        final List<Contact> recentChatContacts = appFrame.getContactsFrame().getRecentChatContacts();

        Utils.printContacts(recentChatContacts);
        final int selected = Utils.selectContact();

        final Contact contact = recentChatContacts.get(selected);
        final ChatFrame contactChatFrame = appFrame.getChatFrameForContact(contact);
        contactChatFrame.addListener(new ChatListener() {
            @Override
            public void onStatusChanged(String newStatus) {
            }

            @Override
            public void onNewMessage(Message message) {
                if (message.getType() == Message.MessageType.IN)
                    System.out.println(message.getTimestamp() + " - " + contact.getContactName() + ":\t" + message.getText());
                else
                    System.out.println(message.getTimestamp() + " - YOU:\t" + message.getText());
            }

            @Override
            public void onNewIncomingMessage(Message incomingMessage) {

            }

            @Override
            public void onChatNotAvailable() {

            }
        });

        String line;
        Scanner scanner = new Scanner(System.in);
        line = scanner.nextLine();
        while (!line.equals("!q")) {
            contactChatFrame.sendText(line.trim());
            line = scanner.nextLine();
        }
        api.shutdownDriver();
        System.out.println("Quitting");
    }
}
