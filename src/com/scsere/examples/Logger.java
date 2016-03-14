package com.scsere.examples;

import com.scsere.main.WaWebStats;
import com.scsere.main.WhatsAppStatusListener;
import com.scsere.main.chat.ChatFrame;
import com.scsere.main.chat.ChatListener;
import com.scsere.main.chat.Message;
import com.scsere.main.contacts.Contact;

import java.util.List;

/**
 * Created by scsere on 10/03/16.
 * Project: waWebStats
 * Example program that lets you choose a
 * contact and reports any changes in status
 * or new messages.
 */
public class Logger {

    public static final String WA_WEB_URL = "https://web.whatsapp.com/";

    public static void main(String[] args) {
        WaWebStats webStats = new WaWebStats();

        webStats.initFirefox();

        //Fetch all available contacts
        List<Contact> contacts = webStats.getWebAppFrame().getContacts();
        //Print all contacts and read user choice
        Utils.printContacts(contacts);
        int selectedContactIndex = Utils.selectContact();

        System.out.println("Selected: " + contacts.get(selectedContactIndex).getContactName());

        webStats.addListener(new WhatsAppStatusListener() {
            @Override
            public void onWhatsappPhoneDisconnect() {
                System.err.println("Phone disconnected");
            }

            @Override
            public void onWhatsappBatteryLow() {
                System.err.println("Phone battery low");
            }

            @Override
            public void onWhatsappRequestedByOtherApplication() {
                System.out.println("Another application requested Whatsapp-Web");
            }
        });

        //Open chat window for selected contact
        ChatFrame chatFrame = webStats.getWebAppFrame().getChatFrameForContact(contacts.get(selectedContactIndex));

        chatFrame.addListener(new ChatListener() {
            @Override
            public void onStatusChanged(String newStatus) {
                System.out.println("Status changed: " + newStatus);
            }

            @Override
            public void onNewMessage(Message message) {
                if (message.getType() == Message.MessageType.OUT)
                    System.out.println("New outgoing message: " + message.getText());
            }

            @Override
            public void onNewIncomingMessage(Message incomingMessage) {
                System.out.println("New incoming message: " + incomingMessage);
            }

            @Override
            public void onChatNotAvailable() {
                System.err.println("Chat no longer available!");
            }
        });
    }
}
