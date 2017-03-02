package com.pragmabits.babelj.notify;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Mac OS X Mountain Lion
 * requires https://github.com/alloy/terminal-notifier
 *
 * @author Diego González
 */
public class MacOsXNotifier implements Notifier {

    private static final String CLI_NOTIFY_APP = "/Applications/terminal-notifier.app/Contents/MacOS/terminal-notifier";
    private RuntimeExec runtime;

    MacOsXNotifier() {
        this.runtime = new RuntimeExec();
    }

    MacOsXNotifier(RuntimeExec runtime) {
        this.runtime = runtime;
    }

    @Override
    public boolean isSupported() {
        try {
            return runtime.exec(CLI_NOTIFY_APP + " > /dev/null 2>&1").waitFor() == 0;
        } catch (InterruptedException | IOException e) {
            return false;
        }
    }

    @Override
    public int sendNotification(Notification notification) throws NotifyException {
        List<String> command = new ArrayList<>(5);
        command.add(CLI_NOTIFY_APP);
        command.add("-title");
        command.add("ℹ " + notification.title);
        command.add("-message");
        command.add(notification.message);
        try {
            return runtime.exec(command.toArray(new String[0])).waitFor();
        } catch (Exception e) {
            throw new NotifyException("Unable to notify with notify OSD", e);
        }
    }

}