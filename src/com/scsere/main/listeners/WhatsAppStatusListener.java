package com.scsere.main.listeners;

/**
 * Created by scsere on 11/03/16.
 * Project: waWebStats
 */
public interface WhatsAppStatusListener {

    void onWhatsappPhoneDisconnect();

    void onWhatsappBatteryLow();

    void onWhatsappRequestedByOtherApplication();

}
