package com.pragmabits.notify;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

/**
 * The type Mac os x notifier test.
 */
public class MacOsXNotifierTest {

    private MacOsXNotifier notifier;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        this.notifier = new MacOsXNotifier(new MacOsXExecMock());
    }

    /**
     * Is supported.
     *
     * @throws Exception the exception
     */
    @Test
    public void isSupported() throws Exception {
        assertTrue("MacOsXNotifier support", notifier.isSupported());
    }

    /**
     * Send notification.
     *
     * @throws Exception the exception
     */
    @Test
    public void sendNotification() throws Exception {
        int result = notifier.sendNotification(new Notification("title", "message"));
        assertTrue("MacOsXNotifier send notification", result == 0);
    }

}