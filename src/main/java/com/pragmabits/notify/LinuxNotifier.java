package com.pragmabits.notify;

import com.pragmabits.RuntimeExec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * NotifyHelper Implementation for Linux<br>
 * Requires notify-send command<br>
 * sudo apt-get install libnotify-bin<br>
 * <p>
 * http://www.barregren.se/blog/pop-notification-command-line
 *
 * @author Diego González
 */
public class LinuxNotifier implements Notifier {

    private final RuntimeExec runtime;

    LinuxNotifier() {
        this.runtime = new RuntimeExec();
    }

    LinuxNotifier(RuntimeExec runtime) {
        this.runtime = runtime;
    }

    @Override
    public boolean isSupported() {
        try {
            return runtime.exec("notify-send --help > /dev/null 2>&1").waitFor() == 0;
        } catch (InterruptedException | IOException e) {
            return false;
        }
    }

    @Override
    public int sendNotification(Notification notification) throws NotifyError {
        List<String> command = new ArrayList<>(5);
        command.add("notify-send");
        command.add("-i");
        command.add("dialog-information");
        command.add(notification.title);
        command.add(notification.message);
        try {
            return runtime.exec(command.toArray(new String[0])).waitFor();
        } catch (InterruptedException | IOException e) {
            throw new NotifyError("Unable to notify with notify-send OSD", e);
        }
    }
}