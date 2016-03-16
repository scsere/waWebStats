package com.scsere.main.contacts;

import com.scsere.main.Watcher;

import java.util.List;

/**
 * Created by scsere on 16/03/16.
 * Project: waWebStats
 */
public class ContactsWatcher extends Watcher<ContactsFrame, ContactsListener>{


    public ContactsWatcher(ContactsFrame parent) {
        super(parent);
    }

    @Override
    protected void performChecks(List<ContactsListener> listeners) {
        checkForUnreadMessages();
        checkContactsOrder();
    }

    private void checkForUnreadMessages() {
        //TODO
    }

    private void checkContactsOrder(){
        //TODO (save contact with index 0 and compare each time)
    }
}
