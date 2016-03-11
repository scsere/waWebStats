package com.scsere.main;

import com.scsere.main.chat.ChatFrame;
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
import java.util.List;

/**
 * Created by Viktor Gobbi on 11/03/16.
 * Project: waWebStats
 */
public class WaWebStats {

    public static final String WA_WEB_URL = "https://web.whatsapp.com/";
    public static String PROFILE_NAME = "SELENIUM";

    private WebDriver driver;
    private boolean interactiveMode = true;

    public WaWebStats() {

    }

    public WaWebStats(String profileName){
        WaWebStats.PROFILE_NAME = profileName;
    }

    public void initFirefox() {
        System.out.println("Initializing waWebStats");
        System.out.println("Loading firefox web driver ...");

        //Load selenium profile
        ProfilesIni profile = new ProfilesIni();
        FirefoxProfile ffprofile = profile.getProfile(PROFILE_NAME);
        driver = new FirefoxDriver(ffprofile);

        System.out.println("Going to WhatsApp-Web ("+WA_WEB_URL+")");

        //Go to WA-web
        driver.get(WA_WEB_URL);

        //Wait until web app is ready
        waitUntilAppReady(driver);
    }

    public List<Contact> getContacts(){
        //TODO: Scroll down to trigger reload of contact list
        List<Contact> contacts = new ArrayList<>();
        for (WebElement element : driver.findElements(By.cssSelector(".infinite-list-item, .infinite-list-item-transition")))
            contacts.add(new Contact(element));
        return contacts;
    }

    public ChatFrame getChatFrameForContact(Contact contact){
        openChatWindowForContact(contact);
        return new ChatFrame(driver.findElement(By.id("main")));
    }

    public void openChatWindowForContact(Contact contact){
        if (contact != null)
            contact.getWebElement().click();
    }


    private static void waitUntilAppReady(WebDriver webDriver) {
        WebElement element = (new WebDriverWait(webDriver, 20))
                .until(ExpectedConditions.elementToBeClickable(By.className("pane-list-user")));
        if (element == null) {
            System.err.println("Could not reach whatsapp-web within 20sec");
            System.exit(1);
        }
    }

}
