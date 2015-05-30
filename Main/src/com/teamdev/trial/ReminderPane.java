package com.teamdev.trial;

import javax.swing.*;
import java.awt.*;

import static java.awt.GridBagConstraints.*;

/**
 * @author Vladimir Ikryanov
 */
public class ReminderPane extends JPanel {
    public ReminderPane() {
        setLayout(new GridBagLayout());
        add(createInfoPane(), new GridBagConstraints(
                0, 0, 1, 1, 1.0, 0.0, WEST, HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        add(new JButton("Send"), new GridBagConstraints(
                1, 0, 1, 1, 0.0, 0.0, WEST, NONE, new Insets(0, 0, 0, 0), 0, 0));
        add(new JButton("Cancel"), new GridBagConstraints(
                2, 0, 1, 1, 0.0, 0.0, WEST, NONE, new Insets(0, 0, 0, 0), 0, 0));
    }

    private Component createInfoPane() {
        JPanel result = new JPanel(new BorderLayout());
        result.add(new JLabel("John Doe"), BorderLayout.CENTER);
        result.add(new JLabel("Send Week email"), BorderLayout.SOUTH);
        return result;
    }
}
