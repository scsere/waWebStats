package com.scsere.main;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by scsere on 11/03/16.
 * Project: waWebStats
 */
public abstract class Listenable<T> {

    protected Watcher watcher;
    protected List<T> listeners = new ArrayList<>();

    public void addListener(T listener) {
        listeners.add(listener);
        if (watcher == null)
            startWatcher();
    }

    public boolean removeListener(T listener) {
        if (!listeners.contains(listener))
            return false;
        final boolean remove = listeners.remove(listener);
        //If there are no more registered listeners, stop the watcher thread
        if (listeners.isEmpty())
            stopWatcher();
        return remove;
    }

    protected abstract void startWatcher();

    protected abstract void stopWatcher();

    public List<T> getListeners() {
        return listeners;
    }
}
