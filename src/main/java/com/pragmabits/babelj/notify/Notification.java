package com.pragmabits.babelj.notify;

import javax.swing.*;

/**
 * Notification class - represents the notification itself
 */
class Notification {
    /**
     * The Image icon.
     */
    ImageIcon imageIcon;
    /**
     * The Title.
     */
    String title;
    /**
     * The Message.
     */
    String message;
    /**
     * The Hide delay.
     */
    int hideDelay = 5000;

    /**
     * Instantiates a new Notification.
     *
     * @param title   the title
     * @param message the message
     */
    Notification(String title, String message) {
        this.title = title;
        this.message = message;
        this.imageIcon = new ImageIcon(
                this.getClass().getResource("/drawable/transClipper-filled-32.png")
        );
    }

    /**
     * Instantiates a new Notification.
     *
     * @param title     the title
     * @param message   the message
     * @param hideDelay the hide delay
     */
    Notification(String title, String message, int hideDelay) {
        this(title, message);
        this.hideDelay = hideDelay;
    }
}
