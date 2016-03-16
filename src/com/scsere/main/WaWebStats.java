package com.scsere.main;

import com.scsere.main.WebApp.WebAppFrame;
import com.scsere.main.WebApp.WebAppWatcher;
import com.scsere.main.WebApp.WhatsAppStatusListener;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by scsere on 11/03/16.
 * Project: waWebStats
 */
public class WaWebStats extends Listenable<WhatsAppStatusListener> {

    public static final String WA_WEB_URL = "https://web.whatsapp.com/";
    public static String PROFILE_NAME = "SELENIUM";

    private WebDriver driver;
    private WebAppFrame appFrame;

    public WaWebStats() {

    }

    public WaWebStats(String profileName) {
        WaWebStats.PROFILE_NAME = profileName;
    }

    public void initFirefox() {
        System.out.println("Initializing waWebStats");
        System.out.println("Loading firefox web driver ...");

        //Load selenium profile
        ProfilesIni profile = new ProfilesIni();
        FirefoxProfile ffprofile = profile.getProfile(PROFILE_NAME);
        driver = new FirefoxDriver(ffprofile);

        System.out.println("Going to WhatsApp-Web (" + WA_WEB_URL + ")");

        //Go to WA-web
        driver.get(WA_WEB_URL);

        //Wait until web app is available
        waitUntilAppReady();
    }

    public void scrollToElement(WebElement element, int xOffset, int yOffset){
        Actions action = new Actions(driver);
        action.moveToElement(element, xOffset, yOffset);
        action.perform();
    }

    private void waitUntilAppReady() {
        WebElement element = (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("app")));
        if (element == null) {
            System.err.println("Could not reach whatsapp-web within 20sec");
            System.exit(1);
        }
        //Wait until web app is ready to use
        waitUntilReady();
        appFrame = new WebAppFrame(element);
    }

    private void waitUntilReady() {
        WebElement element = null;
        try {
            element = (new WebDriverWait(driver, 20))
                    .until(ExpectedConditions.elementToBeClickable(By.className("pane-list-user")));
        } catch (TimeoutException e) {
        }
        if (element == null) {
            if (!driver.findElements(By.cssSelector("div.popup-container div.popup")).isEmpty()) {
                System.err.println("Phone is not reachable");
                System.exit(1);
            }
        }
    }

    public WebAppFrame getWebAppFrame() {
        return appFrame;
    }

    @Override
    protected void startWatcher() {
        watcher = new WebAppWatcher(this);
    }

    @Override
    protected void stopWatcher() {
        watcher.setActive(false);
        watcher = null;
    }
}
