package com.pragmabits.notify;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class AgnosticNotifierTest {

    private AgnosticNotifier notifier;
    @Before
    public void setUp() throws Exception {
        this.notifier = new AgnosticNotifier();
    }

    @Test
    public void isSupported() throws Exception {
        assertTrue("AgnosticNotifier support", notifier.isSupported());
    }

    @Test
    public void sendNotification() throws Exception {
        int result = notifier.sendNotification(new Notification("title", "message", 1));
        assertTrue("MacOsXNotifier send notification", result == 0);
    }
}