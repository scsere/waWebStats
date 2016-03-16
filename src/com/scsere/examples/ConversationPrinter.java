package com.scsere.examples;

import com.scsere.main.WaWebStats;
import com.scsere.main.chat.ChatFrame;
import com.scsere.main.chat.Message;
import com.scsere.main.contacts.Contact;

import java.util.List;

/**
 * Created by scsere on 14/03/16.
 * Project: waWebStats
 */
public class ConversationPrinter {
    public static void main(String[] args) {
        WaWebStats webStats = new WaWebStats();

        webStats.initFirefox();

        //Fetch all available contacts
        List<Contact> contacts = webStats.getWebAppFrame().getContactsFrame().getContacts();
        //Print all contacts and read user choice
        Utils.printContacts(contacts);
        final int selectedContactIndex = Utils.selectContact();

        System.out.println("Selected: " + contacts.get(selectedContactIndex).getContactName());

        ChatFrame chatFrame = webStats.getWebAppFrame().getChatFrameForContact(contacts.get(selectedContactIndex));

        final List<Message> messageList = chatFrame.getMessages();

        for (Message message : messageList)
            System.out.println(message.getType().toString()+"\tAuthor:\t"+message.getAuthor()+"\tMsg:\t"+message.getText());
    }
}
