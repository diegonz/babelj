package com.pragmabits.notify;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * OS Agnostic Java-based (Swing) Notifier Implementation
 *
 * @author Diego González
 */
public class AgnosticNotifier implements Notifier {

    private JFrame jFrame;
    private static final int TARGET_FRAME_WIDTH = 250;

    private JPanel buildHeading(Notification notification) {
        JPanel headingPanel = new JPanel(new BorderLayout());
        headingPanel.setBorder(BorderFactory.createEmptyBorder(2, 0, 4, 0));

        JLabel headingLabel = new JLabel(notification.title);
        headingLabel.setIcon(notification.imageIcon);
        headingLabel.setFont(new Font(Font.SERIF, Font.BOLD, 14));
        headingLabel.setOpaque(false);

        JButton closeButton = new JButton(new AbstractAction("❌") {
            @Override
            public void actionPerformed(final ActionEvent e) {
                jFrame.dispose();
            }
        });
        closeButton.setMargin(new Insets(1, 2, 1, 2));
        closeButton.setFocusable(false);

        headingPanel.add(headingLabel, BorderLayout.WEST);
        headingPanel.add(closeButton, BorderLayout.EAST);

        return headingPanel;
    }

    private JPanel buildContentPanel(Notification notification) {
        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.setBorder(BorderFactory.createEmptyBorder(0, 4, 2, 4));
        String targetText = String.format("<html><div style=\"width:%dpx;\">%s</div></html>",
                TARGET_FRAME_WIDTH - 70,
                notification.message);
        JLabel textLabel = new JLabel(targetText);
        textLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 10));
        textPanel.add(new JLabel(targetText), BorderLayout.CENTER);
        return textPanel;
    }

    private void setupNotification(Notification notification) {
        jFrame = new JFrame(notification.title);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel backgroundPanel = new JPanel(new BorderLayout());
        backgroundPanel.setBorder(BorderFactory.createEmptyBorder(2, 4, 2, 4));
        backgroundPanel.add(this.buildHeading(notification), BorderLayout.NORTH);
        backgroundPanel.add(this.buildContentPanel(notification), BorderLayout.CENTER);
        jFrame.getContentPane().add(backgroundPanel);
    }

    private void showNotification(Notification n) {
        this.setupNotification(n);

        jFrame.setResizable(false);
        jFrame.setAlwaysOnTop(true);
        jFrame.setUndecorated(true);
        jFrame.pack();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Insets toolHeight = Toolkit.getDefaultToolkit().getScreenInsets(jFrame.getGraphicsConfiguration());
        jFrame.setLocation(screenSize.width - jFrame.getWidth(), toolHeight.top);

        jFrame.setVisible(true);

        new Thread(() -> {
            try {
                Thread.sleep(n.hideDelay); // Notification close delay
                this.jFrame.dispose();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    /**
     * Test support on this Operating System, depending on installed software
     *
     * @return boolean value representing specific notifier availability
     */
    @Override
    public boolean isSupported() {
        return true; // Java swing-based notifications should be always available while on GUI.
    }

    /**
     * sendNotification sends a notification to the user
     *
     * @param notification Object containing notification data
     */
    @Override
    public int sendNotification(Notification notification) {
        new Thread(() -> this.showNotification(notification)).start();
        try {
            Thread.sleep(notification.hideDelay); // Notification close delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return 0;
    }
}
