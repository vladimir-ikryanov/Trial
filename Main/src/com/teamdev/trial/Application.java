package com.teamdev.trial;

import javax.swing.*;

/**
 * @author Vladimir Ikryanov
 */
public class Application {
    public static void main(String[] args) {
        ApplicationSettings settings = new ApplicationSettings();
        ApplicationContext context = new ApplicationContext(settings);
        context.load();

        ApplicationFrame frame = new ApplicationFrame(context);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setSize(1200, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
