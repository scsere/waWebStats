package com.scsere.main;

import java.util.List;

/**
 * Created by scsere on 11/03/16.
 * Project: waWebStats
 */
public class WebAppWatcher extends Watcher<WaWebStats, WhatsAppStatusListener> {

    public WebAppWatcher(WaWebStats parent) {
        super(parent);
    }

    @Override
    protected void performChecks(List<WhatsAppStatusListener> listeners) {

    }
}
