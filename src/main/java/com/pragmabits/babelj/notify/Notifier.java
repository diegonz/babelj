package com.pragmabits.babelj.notify;

/**
 * Notifier Interface
 *
 * @author Diego González
 */
public interface Notifier {

    /**
     * Test support on this Operating System, depending on installed software
     *
     * @return boolean value representing specific notifier availability
     */
    boolean isSupported();

    /**
     * sendNotification sends a notification to the user
     *
     * @param notification Object containing notification data
     * @throws NotifyError the notify exception
     */
    int sendNotification(Notification notification) throws NotifyError;
}
