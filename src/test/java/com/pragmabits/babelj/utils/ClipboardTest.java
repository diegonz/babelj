package com.pragmabits.babelj.utils;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * The type Clipboard test.
 */
public class ClipboardTest {

    private static final String testString = "This is a test String";
    private Clipboard clipboard;

    @Before
    public void setUp() throws Exception {
        clipboard = Clipboard.getInstance();
    }

    /**
     * Gets by input type.
     *
     * @throws Exception the exception
     */
    @Test
    public void getByInputType() throws Exception {
        clipboard.setClipboard(testString);
        assertEquals("Get clipboard", testString, clipboard.getByInputType("clipboard"));
        assertNotEquals("Get selection", testString, clipboard.getByInputType("selection"));
    }

    /**
     * Sets clipboard.
     *
     * @throws Exception the exception
     */
    @Test
    public void setClipboard() throws Exception {
        clipboard.setClipboard(testString);
        assertEquals("Set clipboard", testString, clipboard.getByInputType("clipboard"));
    }

}