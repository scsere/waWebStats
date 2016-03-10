package com.scsere.main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;

/**
 * Created by scsere on 10/03/16.
 * Project: waWebStats
 */
public class Main {

    public static final String WA_WEB_URL = "https://web.whatsapp.com/";

    public static void main(String[] args) {
        System.out.println("Hello World!");

        //Load selenium profile
        ProfilesIni profile = new ProfilesIni();
        FirefoxProfile ffprofile = profile.getProfile("SELENIUM");
        WebDriver webDriver = new FirefoxDriver(ffprofile);

        //Go to WA-web
        webDriver.get(WA_WEB_URL);
    }
}
