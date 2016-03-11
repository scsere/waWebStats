package com.scsere.main;

import com.scsere.main.chat.ChatFrame;
import com.scsere.main.chat.ChatListener;
import com.scsere.main.chat.Message;
import com.scsere.main.contacts.Contact;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by scsere on 10/03/16.
 * Project: waWebStats
 * Example program that lets you choose a
 * contact and reports any changes in status
 * or new messages.
 */
public class Main {

    public static final String WA_WEB_URL = "https://web.whatsapp.com/";

    public static void main(String[] args) {
        WaWebStats webStats = new WaWebStats();

        webStats.initFirefox();

        //Fetch all available contacts
        List<Contact> contacts = webStats.getContacts();
        //Print all contacts and read user choice
        printContacts(contacts);
        int selectedContactIndex = selectContact();

        System.out.println("Selected: " + contacts.get(selectedContactIndex).getContactName());

        //Open chat window for selected contact
        ChatFrame chatFrame = webStats.getChatFrameForContact(contacts.get(selectedContactIndex));

        chatFrame.registerChatListener(new ChatListener() {
            @Override
            public void onStatusChanged(String newStatus) {
                System.out.println("Status changed: " + newStatus);
            }

            @Override
            public void onNewMessage(Message message) {
            }

            @Override
            public void onNewIncomingMessage(Message incomingMessage) {
                System.out.println("New incoming message: " + incomingMessage);
            }
        });

        while (true) ;
    }


    private static int selectContact() {
        final int i;
        try {
            System.out.print("Select a contact:");
            i = new Scanner(System.in).nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid number");
            //TODO: Remove recursive call
            return selectContact();
        }
        return i;
    }

    private static void printContacts(List<Contact> contacts) {
        System.out.println("Contacts:");
        for (int i = 0, contactsSize = contacts.size(); i < contactsSize; i++) {
            Contact contact = contacts.get(i);
            System.out.println("\t[" + i + "] " + contact.getContactName() + "\t\t\tlast msg:" + contact.getContactMeta());
        }
    }

}
