package com.scsere.main.WebApp;

import com.scsere.main.WaWebStats;
import com.scsere.main.Watcher;
import com.scsere.main.WhatsAppStatusListener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by scsere on 11/03/16.
 * Project: waWebStats
 */
public class WebAppWatcher extends Watcher<WaWebStats, WhatsAppStatusListener> {

    private boolean lastRequestedByOther = false;
    private boolean lastBatteryLow = false;
    private boolean lastPhoneDisconnected = false;

    public WebAppWatcher(WaWebStats parent) {
        super(parent);
    }


    @Override
    public void run() {
        super.run();
    }

    @Override
    protected void performChecks(List<WhatsAppStatusListener> listeners) {
        final WebElement webAppFrameElement = parent.getWebAppFrame().getWebAppFrameElement();
        //Search for elements indicating one of the states: requested by other app, disconnected or battery low
        final boolean requestedByOther = !webAppFrameElement.findElements(By.cssSelector("div.popup-container div.popup")).isEmpty();
        final boolean batteryLow = !webAppFrameElement.findElements(By.cssSelector("div.butterbar-battery")).isEmpty();
        final boolean phoneDisconnected = !webAppFrameElement.findElements(By.cssSelector("div.butterbar-phone")).isEmpty();
        for (WhatsAppStatusListener listener : listeners) {
            //Check if requested by other has changed
            if (requestedByOther && requestedByOther != lastRequestedByOther) {
                listener.onWhatsappRequestedByOtherApplication();
                lastRequestedByOther = requestedByOther;
            } else if (requestedByOther != lastRequestedByOther)
                lastRequestedByOther = requestedByOther;

            //Check if battery low state has changed
            if (batteryLow && batteryLow != lastBatteryLow) {
                listener.onWhatsappBatteryLow();
                lastBatteryLow = batteryLow;
            } else if (batteryLow != lastBatteryLow)
                lastBatteryLow = batteryLow;

            //Check if phone disconnected state changed
            if (phoneDisconnected && phoneDisconnected != lastPhoneDisconnected) {
                listener.onWhatsappPhoneDisconnect();
                lastPhoneDisconnected = phoneDisconnected;
            } else if (phoneDisconnected != lastPhoneDisconnected)
                lastPhoneDisconnected = phoneDisconnected;
        }
    }
}
