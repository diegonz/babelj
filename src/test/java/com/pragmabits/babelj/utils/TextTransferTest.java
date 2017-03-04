package com.pragmabits.babelj.utils;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * The TextTransfer test.
 */
public class TextTransferTest {

    private static final String testString = "This is a test String";
    private TextTransfer clipboard;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        clipboard = new TextTransfer();
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