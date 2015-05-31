package com.teamdev.trial;

import javax.swing.*;

/**
 * @author Vladimir Ikryanov
 */
public class Application {
    public static void main(String[] args) {
        ApplicationSettings settings = new ApplicationSettings();
        final ApplicationContext context = new ApplicationContext(settings);
        try {
            context.load();
        } catch (Exception e) {
            throw new RuntimeException("Failed to load app context.", e);
        }

        ApplicationFrame frame = new ApplicationFrame(context);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
