package com.teamdev.trial;

import javax.swing.*;

/**
 * @author Vladimir Ikryanov
 */
public class Application {
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        ApplicationSettings settings = new ApplicationSettings();
        ApplicationContext context = new ApplicationContext(settings);
        context.load();

        ApplicationFrame frame = new ApplicationFrame(context);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setSize(1280, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
