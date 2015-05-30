package com.teamdev.trial;

import javax.swing.*;
import java.awt.*;

import static java.awt.GridBagConstraints.*;

/**
 * @author Vladimir Ikryanov
 */
public class PipelinePane extends JPanel {
    public PipelinePane() {
        setLayout(new GridBagLayout());
        add(createCustomerPane(), new GridBagConstraints(
                0, 0, 1, 1, 0.0, 0.0, WEST, NONE, new Insets(0, 0, 0, 0), 0, 0));
        add(createPipelinePane(), new GridBagConstraints(
                1, 0, 1, 1, 1.0, 0.0, WEST, HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
    }

    private Component createCustomerPane() {
        JPanel result = new JPanel(new BorderLayout());
        result.add(new JLabel("John Doe"), BorderLayout.CENTER);
        result.add(new JLabel("john.doe@gmail.com"), BorderLayout.SOUTH);
        return result;
    }

    private Component createPipelinePane() {
        JPanel result = new JPanel(new FlowLayout(FlowLayout.LEFT));
        result.setBackground(Color.RED);
        result.add(new JLabel("Start"));
        result.add(new JLabel("Day"));
        result.add(new JLabel("Week"));
        result.add(new JLabel("Offer"));
        result.add(new JLabel("Pre-finish"));
        result.add(new JLabel("Feedback"));
        result.add(new JLabel("Finish"));
        return result;
    }
}
