package com.pragmabits.notify;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class LinuxNotifierTest {

    private LinuxNotifier notifier;

    @Before
    public void setUp() throws Exception {
        this.notifier = new LinuxNotifier(new LinuxExecMock());
    }

    @Test
    public void isSupported() throws Exception {
        assertTrue("LinuxNotifier support", notifier.isSupported());
    }

    @Test
    public void sendNotification() throws Exception {
        int result = notifier.sendNotification(new Notification("title", "message"));
        assertTrue("LinuxNotifier send notification", result == 0);
    }
}