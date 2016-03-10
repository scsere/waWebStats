package com.scsere.main;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.util.Date;

/**
 * Created by scsere on 10/03/16.
 * Project: waWebStats
 */
public class OnlineListener extends Thread {

    private WebElement element;
    private String lastState = "";
    private int interval = 100;
    private volatile boolean active = true;

    public OnlineListener(WebElement element) {
        super();
        this.element = element;
        this.start();
    }

    @Override
    public void run() {
        while (active) {
            try {
                String currentText = element.getText();
                if (!currentText.equals(lastState)) {
                    System.out.println(new Date().toString() + ": " + currentText);
                    lastState = currentText;
                }

                try {
                    Thread.sleep(interval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }catch (StaleElementReferenceException e){
                setActive(false);
            }
        }
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public String getLastState() {
        return lastState;
    }
}