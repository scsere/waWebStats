package com.scsere.main.contacts;

import com.scsere.main.Listenable;
import com.scsere.main.WaWebStats;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by scsere on 16/03/16.
 * Project: waWebStats
 */
public class ContactsFrame extends Listenable<ContactsListener>{

    protected WebElement frame;

    public ContactsFrame(WebElement frame){
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

    public List<Contact> getAllChatContacts(){
        List<Contact> contacts = new ArrayList<>();
        List<WebElement> contactElements = null;
        List<WebElement> moreContactElements = null;
        do {
            contactElements = frame.findElements(By.cssSelector(".infinite-list-item, .infinite-list-item-transition"));
            //If there are no contacts return null
            if (contactElements.isEmpty())
                return null;
            //Scroll down and sleep for 300 milliseconds
            WaWebStats.scrollToElement(contactElements.get(contactElements.size()-1), 0, -40);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            moreContactElements  = frame.findElements(By.cssSelector(".infinite-list-item, .infinite-list-item-transition"));
        }while (moreContactElements.size() > contactElements.size());
        for (WebElement element : moreContactElements)
            contacts.add(new Contact(element));
        return contacts;
    }

    public List<Contact> getAllContacts(){
        //TODO: Click on "New chat" and load all contacts
        return null;
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
