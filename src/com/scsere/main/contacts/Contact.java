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

    public Contact(WebElement webElement) {
        this.webElement = webElement;
        this.index = getChatIndex();
    }

    public int getChatIndex() {
        final String style = webElement.getAttribute("style");
        final Matcher matcher = Pattern.compile("(\\d+)%").matcher(style);
        if (matcher.find())
            return Integer.parseInt(matcher.group(1)) / 100;
        return -1;
    }

    public String getContactName() {
        if (webElement != null)
            return webElement.findElement(By.cssSelector(".chat-title")).getText();
        return null;
    }

    public String getContactMeta() {
        if (webElement != null)
            return webElement.findElement(By.cssSelector(".chat-meta")).getText();
        return null;
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
