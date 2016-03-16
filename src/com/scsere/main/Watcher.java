package com.scsere.main;

import java.util.List;

/**
 * Created by scsere on 11/03/16.
 * Project: waWebStats
 */
public abstract class Watcher<T extends Listenable<L>, L> extends Thread {

    public static final int DEFAULT_INTERVAL = 250;

    protected T parent;
    protected int interval;
    protected boolean active = true;

    public Watcher(T parent, boolean daemon){
        this.parent = parent;
        this.interval = DEFAULT_INTERVAL;
        this.setDaemon(daemon);
        this.start();
    }

    public Watcher(T parent) {
        this(parent, false);
    }

    @Override
    public void run() {
        while (active) {
            performChecks(parent.getListeners());
            waitInterval();
        }
    }

    protected abstract void performChecks(List<L> listeners);

    protected void waitInterval() {
        try {
            Thread.sleep(interval);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
