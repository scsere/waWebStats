package com.scsere.main;

import java.util.List;

/**
 * Created by scsere on 11/03/16.
 * Project: waWebStats
 */
public abstract class Watcher<T extends Listenable<L>, L> extends Thread {

    public static final int DEFAULT_INTERVAL = 5000;

    protected T parent;
    protected int interval;
    protected boolean active = true;

    public Watcher(T parent) {
        this.parent = parent;
        this.interval = DEFAULT_INTERVAL;
        this.setDaemon(true);
        this.start();
    }

    @Override
    public void run() {
        performChecks(parent.getListener());
        waitInterval();
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
