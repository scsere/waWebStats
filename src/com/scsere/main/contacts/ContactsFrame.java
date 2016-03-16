package com.scsere.main.contacts;

import com.scsere.main.Listenable;
import com.scsere.main.WaWebStats;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by scsere on 16/03/16.
 * Project: waWebStats
 */
public class ContactsFrame extends Listenable<ContactsListener> {

    protected WebElement frame;

    public ContactsFrame(WebElement frame) {
        this.frame = frame;
    }

    public List<Contact> getRecentChatContacts() {
        List<Contact> contacts = new ArrayList<>();
        final List<WebElement> contactElements = frame.findElements(By.cssSelector(".infinite-list-item, .infinite-list-item-transition"));
        //If there are no contacts return null
        if (contactElements.isEmpty())
            return null;
        for (WebElement element : contactElements)
            contacts.add(new Contact(element));
        return contacts;
    }

    public List<Contact> getAllChatContacts() {
        //TODO: This one can be improved A LOT, it takes ages to load all contacts if there are more than 20 or 30
        List<Contact> contacts = new ArrayList<>();
        List<WebElement> contactElements = null;
        List<WebElement> moreContactElements = null;
        do {
            contacts.clear();
            contactElements = frame.findElements(By.cssSelector(".infinite-list-item, .infinite-list-item-transition"));
            //If there are no contacts return null
            if (contactElements.isEmpty())
                return null;

            for (WebElement element : contactElements)
                contacts.add(new Contact(element));
            Collections.sort(contacts);
            //Scroll down and sleep for 300 milliseconds
            WaWebStats.scrollToElement(contacts.get(contacts.size() - 1).getWebElement(), 0, -40);
            try {
                Thread.sleep(50);//TODO Replace with driverWait, constants take way too long
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            moreContactElements = frame.findElements(By.cssSelector(".infinite-list-item, .infinite-list-item-transition"));
        } while (moreContactElements.size() > contactElements.size());
        return contacts;
    }

    public List<Contact> getAllContacts() {
        //TODO: Click on "New chat" and load all contacts
        return null;
    }

    public List<Contact> getContactsWithUnreadMessages() {
        List<Contact> result = new ArrayList<>();
        List<Contact> recentContacts = getRecentChatContacts();//TODO: Query all contacts not only recent   
        for (Contact contact : recentContacts) {
            final List<WebElement> unreadMessage = contact.getWebElement().findElements(By.cssSelector("span.icon-meta.unread-count"));
            if (!unreadMessage.isEmpty())
                result.add(contact);
        }
        return result.isEmpty() ? null : result;
    }

    public WebElement getElement() {
        return frame;
    }

    public void setElement(WebElement element) {
        this.frame = element;
    }

    @Override
    protected void startWatcher() {
        watcher = new ContactsWatcher(this);
    }

    @Override
    protected void stopWatcher() {
        watcher.setActive(false);
        watcher = null;
    }
}
