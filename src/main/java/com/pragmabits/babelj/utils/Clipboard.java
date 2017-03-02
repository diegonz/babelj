package com.pragmabits.babelj.utils;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;

/**
 * Clipboard class handles clipboard or selection input.
 * Double try-catch(s) handles clipboard/selection de-serialization exceptions (Ex: IntelliJ products).
 *
 * @author Diego González
 */
public class Clipboard {

    private static Clipboard instance;
    private static Toolkit toolkit = Toolkit.getDefaultToolkit();

    private Clipboard() {
    }

    public static synchronized Clipboard getInstance() {
        if (instance == null) {
            instance = new Clipboard();
        }
        return instance;
    }

    private String getClipboard() throws ClipboardError {
        try {
            return (String) toolkit.getSystemClipboard().getData(DataFlavor.stringFlavor);
        } catch (Exception e) {
            throw new ClipboardError(e.getMessage(), e);
        }
    }

    private String getSelection() throws ClipboardError {
        try {
            return (String) toolkit.getSystemSelection().getData(DataFlavor.stringFlavor);
        } catch (Exception e) {
            throw new ClipboardError(e.getMessage(), e);
        }
    }

    /**
     * Gets user input.
     *
     * @param inputType the input type
     * @return the user input
     * @throws ClipboardError the input error
     */
    public String getByInputType(String inputType) throws ClipboardError {
        switch (inputType) {
            case "clipboard":
                return getClipboard();

            case "selection":
            default:
                return getSelection();
        }
    }

    /**
     * Sets clipboard.
     *
     * @param targetText the target text
     */
    public void setClipboard(String targetText) {
        StringSelection text = new StringSelection(targetText);
        toolkit.getSystemClipboard().setContents(text, text);
    }
}
