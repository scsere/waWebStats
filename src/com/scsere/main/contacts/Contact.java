package com.scsere.main.contacts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by scsere on 10/03/16.
 * Project: waWebStats
 */
public class Contact implements Comparable<Contact> {
    private WebElement webElement;
    public int index;
    public String name;
    public String meta;

    public Contact(WebElement webElement) {
        this.webElement = webElement;
        fetchDataFromWebElement();
    }

    public int getChatIndex() {
        return index;
    }

    public String getContactName() {
        return name;
    }

    public String getContactMeta() {
        return meta;
    }

    public boolean fetchDataFromWebElement(){
        if (webElement==null)
            return false;

        //Get chat index
        final String style = webElement.getAttribute("style");
        final Matcher matcher = Pattern.compile("(\\d+)%").matcher(style);
        this.index = -1;
        if (matcher.find())
            this.index =  Integer.parseInt(matcher.group(1)) / 100;

        //Get contact name
        this.name = webElement.findElement(By.cssSelector(".chat-title")).getText();

        //Get contact meta
        this.meta = webElement.findElement(By.cssSelector(".chat-meta")).getText();
        return true;
    }

    public WebElement getWebElement() {
        return webElement;
    }

    public void setWebElement(WebElement webElement) {
        this.webElement = webElement;
    }

    @Override
    public int compareTo(Contact contact) {
        if (this.index > contact.index)
            return 1;
        if (this.index < contact.index)
            return -1;
        return 0;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name=" + getContactName() +
                ", meta=" + getContactMeta() +
                '}';
    }
}
