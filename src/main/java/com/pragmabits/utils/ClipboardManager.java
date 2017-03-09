package com.pragmabits.utils;

import com.pragmabits.RuntimeExec;

import java.awt.*;
import java.awt.datatransfer.*;
import java.io.IOException;

/**
 * The type Text transfer.
 */
public final class ClipboardManager implements ClipboardOwner, ClipboardHandler {

    /**
     * Empty implementation of the ClipboardOwner interface.
     */
    @Override
    public void lostOwnership(Clipboard clipboard, Transferable contents) {
        //do nothing
    }

    /**
     * Gets text by input type (clipboard or selection).
     *
     * @param inputType the input type
     * @return the by input type
     * @throws ClipboardManagerError the clipboard error
     */
    @Override
    public String getByInputType(String inputType) throws ClipboardManagerError {
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
            } catch (UnsupportedFlavorException | IOException e) {
                throw new ClipboardManagerError(e.getMessage(), e);
            }
        }
        return result;
    }

    /**
     * Place a String on the clipboard. Using Runtime.exec "xclip" on Linux to workaround
     * this Ubuntu derivatives bug: https://wiki.ubuntu.com/ClipboardPersistence
     */
    @Override
    public boolean setClipboard(String targetText) throws ClipboardManagerError {
        if (isUbuntuDerivative()) {
            try {
                String[] command = {"/bin/sh", "-c", "echo -n \"" + targetText + "\" | xclip -sel clip"};
                return new RuntimeExec().exec(command).waitFor() == 0;
            } catch (InterruptedException | IOException e) {
                throw new ClipboardManagerError("Unable to notify with notify-send OSD", e);
            }
        } else {
            StringSelection stringSelection = new StringSelection(targetText);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            //clipboard.setContents(stringSelection, stringSelection);
            //clipboard.setContents(stringSelection, null);
            clipboard.setContents(stringSelection, this);
            return true;
        }
    }

    /**
     * Is ubuntu derivative boolean.
     *
     * @return true if run into an Ubuntu derivative
     */
    private boolean isUbuntuDerivative() {
        return System.getProperty("os.name").toLowerCase().contains("linux") &&
                System.getProperty("os.version").contains("generic") ||
                System.getProperty("os.version").contains("lowlatency");
    }
}