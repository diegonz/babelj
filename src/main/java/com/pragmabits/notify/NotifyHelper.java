package com.pragmabits.notify;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * The Notify Helper class<br>
 *
 * @author Diego González
 */
public class NotifyHelper {

    private static NotifyHelper instance;

    private List<Notifier> potentialNotifiers;

    private NotifyHelper() {
        super();
        this.potentialNotifiers = this.getNotifiersByOs(System.getProperty("os.name").toLowerCase());
    }

    private static synchronized NotifyHelper getInstance() {
        if (instance == null) {
            instance = new NotifyHelper();
        }
        return instance;
    }

    private List<Notifier> getNotifiersByOs(String lowercaseOsName) {
        if (lowercaseOsName.contains("linux") || lowercaseOsName.contains("unix")) {
            return Arrays.asList(new LinuxNotifier(), new AgnosticNotifier());
        } else if (lowercaseOsName.contains("mac") || lowercaseOsName.contains("darwin")) {
            return Arrays.asList(new MacOsXNotifier(), new AgnosticNotifier());
        } else {
            return Collections.singletonList(new AgnosticNotifier()); // Also lowercaseOsName.contains("windows")
        }
    }

    private void doNotify(String title, String message) throws NotifyError {
        for (Notifier notifier : potentialNotifiers) {
            if (notifier.isSupported()) {
                notifier.sendNotification(new Notification(title, message));
                break;
            }
        }
    }

    /**
     * Notify static method, instantiates notifier and do the notifying.
     *
     * @param title   the title
     * @param message the message
     * @throws NotifyError the notify exception
     */
    public static void notify(String title, String message) throws NotifyError {
        getInstance().doNotify(title, message);
    }
}
