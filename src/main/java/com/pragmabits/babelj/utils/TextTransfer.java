package com.pragmabits.babelj.utils;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.*;
import java.io.IOException;

/**
 * The type Text transfer.
 */
public final class TextTransfer implements ClipboardOwner, ClipboardHandler {

    /**
     * Empty implementation of the ClipboardOwner interface.
     */
    @Override
    public void lostOwnership(Clipboard clipboard, Transferable aContents) {
        //do nothing
    }

    /**
     * Gets text by input type (clipboard or selection).
     *
     * @param inputType the input type
     * @return the by input type
     * @throws ClipboardError the clipboard error
     */
    @Override
    public String getByInputType(String inputType) throws ClipboardError {
        Clipboard clipboard = "clipboard".equals(inputType)
                ? Toolkit.getDefaultToolkit().getSystemClipboard()
                : Toolkit.getDefaultToolkit().getSystemSelection();

        String result = "";
        //odd: the Object param of getContents is not currently used
        Transferable contents = clipboard.getContents(null);
        boolean hasTransferableText =
                (contents != null) && contents.isDataFlavorSupported(DataFlavor.stringFlavor);
        if (hasTransferableText) {
            try {
                result = (String) contents.getTransferData(DataFlavor.stringFlavor);
            } catch (UnsupportedFlavorException | IOException ex) {
                throw new ClipboardError(ex.getMessage(), ex);
            }
        }
        return result;
    }

    /**
     * Place a String on the clipboard, and make this class the
     * owner of the Clipboard's contents.
     */
    @Override
    public void setClipboard(String targetText) {
        StringSelection stringSelection = new StringSelection(targetText);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, this);
    }
}