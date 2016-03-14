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
        final boolean requestedByOther = !webAppFrameElement.findElements(By.cssSelector("div.popup-container div.popup")).isEmpty();
        final boolean batteryLow = !webAppFrameElement.findElements(By.cssSelector("div.butterbar-battery")).isEmpty();
        for (WhatsAppStatusListener listener : listeners) {
            if (requestedByOther && requestedByOther != lastRequestedByOther) {
                listener.onWhatsappRequestedByOtherApplication();
                lastRequestedByOther = requestedByOther;
            } else if (requestedByOther != lastRequestedByOther)
                lastRequestedByOther = requestedByOther;

            if (batteryLow && lastBatteryLow != batteryLow) {
                listener.onWhatsappBatteryLow();
                lastBatteryLow = batteryLow;
            } else if (batteryLow != lastBatteryLow)
                lastBatteryLow = batteryLow;
        }
    }
}
