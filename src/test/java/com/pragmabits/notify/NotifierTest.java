package com.pragmabits.notify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.assertTrue;

/**
 * The interface Notifier test.
 */
@RunWith(Parameterized.class)
public class NotifierTest {

    private Notifier notifier;

    /**
     * Instantiates a new Notifier test.
     *
     * @param notifier the notifier
     */
    public NotifierTest(Notifier notifier) {
        this.notifier = notifier;
    }

    /**
     * Is supported true.
     *
     * @throws Exception the exception
     */
    @Test
    public void isSupported_True() throws Exception {
        assertTrue("Notifier isSupported", notifier.isSupported());
    }

    /**
     * Send notification.
     *
     * @throws Exception the exception
     */
    @Test
    public void sendNotification() throws Exception {
        Notification n = new Notification("title", "message", 1);
        assertTrue("Notifier sendNotification", notifier.sendNotification(n) == 0);
    }

    /**
     * Instances to test collection.
     *
     * @return the collection
     */
    @Parameterized.Parameters
    public static Collection<Object[]> instancesToTest() {
        return Collections.singleton(new Object[]{new AgnosticNotifier()});
    }
}