package com.scsere.main.contacts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by scsere on 10/03/16.
 * Project: waWebStats
 */
public class Contact {
    private WebElement webElement;

    public Contact(WebElement webElement) {
        this.webElement = webElement;
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
    public String toString() {
        return "Contact{" +
                "name=" + getContactName() +
                ", meta=" + getContactMeta() +
                '}';
    }
}
