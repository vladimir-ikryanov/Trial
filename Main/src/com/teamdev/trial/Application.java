package com.teamdev.trial;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Vladimir Ikryanov
 */
public class Application {
    public static void main(String[] args) {
        ApplicationSettings settings = new ApplicationSettings();
        final ApplicationContext context = new ApplicationContext(settings);
        try {
            context.load();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Failed to load app context.", e);
        }

        ApplicationFrame frame = new ApplicationFrame(context);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                try {
                    context.save();
                } catch (IOException e) {
                    throw new RuntimeException("Failed to save app context.", e);
                }
            }
        });
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
