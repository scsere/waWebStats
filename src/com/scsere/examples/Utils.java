package com.scsere.examples;

import com.scsere.main.contacts.Contact;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by scsere on 11/03/16.
 * Project: waWebStats
 */
public class Utils {

    public static int selectContact() {
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

    public static void printContacts(List<Contact> contacts) {
        System.out.println("Contacts:");
        for (int i = 0, contactsSize = contacts.size(); i < contactsSize; i++) {
            Contact contact = contacts.get(i);
            System.out.println("\t[" + i + "] " + contact.getContactName() + "\t\t\tlast msg:" + contact.getContactMeta());
        }
    }
}
