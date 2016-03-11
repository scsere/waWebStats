package com.scsere.main;

import com.scsere.main.chat.ChatFrame;
import com.scsere.main.chat.ChatListener;
import com.scsere.main.chat.Message;
import com.scsere.main.contacts.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by scsere on 10/03/16.
 * Project: waWebStats
 */
public class Main {

    public static final String WA_WEB_URL = "https://web.whatsapp.com/";

    public static void main(String[] args) {
        System.out.println("Loading web driver ...");

        //Load selenium profile
        ProfilesIni profile = new ProfilesIni();
        FirefoxProfile ffprofile = profile.getProfile("SELENIUM");
        WebDriver webDriver = new FirefoxDriver(ffprofile);

        //Go to WA-web
        webDriver.get(WA_WEB_URL);

        //Wait until web app is ready
        loadInterface(webDriver);

        //Fetch all available contacts
        List<Contact> contacts = getAllContacts(webDriver);
        //Print all contacts and read user choice
        printContacts(contacts);
        int selectedContactIndex = selectContact();

        System.out.println("Selected: " + contacts.get(selectedContactIndex).getContactName());

        //Open chat window for selected contact
        openContactChat(contacts.get(selectedContactIndex));
        //Get the chat frame
        ChatFrame chatFrame = new ChatFrame(webDriver.findElement(By.id("main")));

        System.out.println(chatFrame.getLastSeenTime());

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

    private static void openContactChat(Contact contact) {
        if (contact != null)
            contact.getWebElement().click();
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
            System.out.println("\t[" + i + "}" + contact.getContactName() + "\tlast msg:" + contact.getContactMeta());
        }
    }

    private static void loadInterface(WebDriver webDriver) {
        WebElement element = (new WebDriverWait(webDriver, 20))
                .until(ExpectedConditions.elementToBeClickable(By.className("pane-list-user")));
        if (element == null) {
            System.err.println("WA-Web was not logging in");
            System.exit(1);
        }
    }

    public static List<Contact> getAllContacts(WebDriver webDriver) {
        //TODO: Scroll down to trigger reload of contact list
        List<Contact> contacts = new ArrayList<>();
        for (WebElement element : webDriver.findElements(By.cssSelector(".infinite-list-item, .infinite-list-item-transition")))
            contacts.add(new Contact(element));
        return contacts;
    }

}
