package com.scsere.main.WebApp;

import com.scsere.main.chat.ChatFrame;
import com.scsere.main.contacts.Contact;
import com.scsere.main.contacts.ContactsFrame;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by scsere on 12/03/16.
 * Project: waWebStats
 */
public class WebAppFrame {

    protected WebElement webAppFrameElement;
    protected boolean ready = false;

    public WebAppFrame(WebElement webAppFrameElement) {
        this.webAppFrameElement = webAppFrameElement;
        validateApplicationFrame();
    }

    /**
     * Check if application frame is present and ready
     */
    public void validateApplicationFrame() {
        if (webAppFrameElement == null) {
            this.ready = false;
            return;
        }

        if (webAppFrameElement.findElements(By.className("pane-list-user")).isEmpty()) {
            System.err.println("panel list users missing");
            this.ready = false;
            return;
        }

        this.ready = true;
    }

    public WebElement getWebAppFrameElement() {
        return webAppFrameElement;
    }

    public void setWebAppFrameElement(WebElement webAppFrameElement) {
        this.webAppFrameElement = webAppFrameElement;
    }

    public void openChatWindowForContact(Contact contact) {
        if (contact != null)
            contact.getWebElement().click();
    }

    public ChatFrame getChatFrameForContact(Contact contact) {
        validateApplicationFrame();
        openChatWindowForContact(contact);
        return new ChatFrame(webAppFrameElement.findElement(By.id("main")));
    }

    public ContactsFrame getContactsFrame(){
        validateApplicationFrame();
        return new ContactsFrame(webAppFrameElement.findElement(By.id("pane-side")));
    }
}
