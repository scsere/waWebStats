package com.scsere.main;

import com.scsere.main.WebApp.WebAppFrame;
import com.scsere.main.WebApp.WebAppWatcher;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

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

        //Wait until web app is ready
        waitUntilAppReady();
    }

    public WebAppFrame getWebAppFrame() {
        return appFrame;
    }

    private void waitUntilAppReady() {
        //TODO: Directly wait for appframe element and remove second statement
        WebElement element = (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.elementToBeClickable(By.className("pane-list-user")));
        if (element == null) {
            System.err.println("Could not reach whatsapp-web within 20sec");
            System.exit(1);
        }

        final List<WebElement> applicationFrameElement = driver.findElements(By.id("app"));
        if (applicationFrameElement.isEmpty() || applicationFrameElement.size() > 1) {
            System.err.println("Could not load application frame. Found "+ applicationFrameElement.size() + " matching elements");
            return;
        }
        appFrame = new WebAppFrame(applicationFrameElement.get(0));
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
