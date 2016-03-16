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

    public List<Contact> getContacts() {
        //TODO: Scroll down to trigger reload of contact list
        List<Contact> contacts = new ArrayList<>();
        final List<WebElement> contactElements = frame.findElements(By.cssSelector(".infinite-list-item, .infinite-list-item-transition"));
        //If there are no contacts return null
        if (contactElements.isEmpty())
            return null;
        for (WebElement element : contactElements)
            contacts.add(new Contact(element));
        return contacts;
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
