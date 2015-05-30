package com.teamdev.trial;

import javax.swing.*;
import java.awt.*;

import static java.awt.GridBagConstraints.*;

/**
 * @author Vladimir Ikryanov
 */
public class ApplicationFrame extends JFrame {
    public ApplicationFrame() throws HeadlessException {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(createSplitPane());
    }

    private JSplitPane createSplitPane() {
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setResizeWeight(1.0);
        splitPane.setBorder(BorderFactory.createEmptyBorder());
        splitPane.setLeftComponent(createLeftPane());
        splitPane.setRightComponent(createRightPane());
        return splitPane;
    }

    private Component createRightPane() {
        JPanel result = new JPanel(new BorderLayout());
        result.setMinimumSize(new Dimension(350, 200));
        result.add(createRightCaption(), BorderLayout.NORTH);
        result.add(createRightContent(), BorderLayout.CENTER);
        return result;
    }

    private JLabel createRightCaption() {
        JLabel result = new JLabel("Reminders");
        result.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 0));
        result.setFont(result.getFont().deriveFont(20.0f));
        return result;
    }

    private Component createRightContent() {
        JPanel result = new JPanel(new BorderLayout());
        result.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        result.add(createRemindersPane(), BorderLayout.CENTER);
        return result;
    }

    private Component createRemindersPane() {
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new GridBagLayout());
        for (int i = 0; i < 50; i++) {
            contentPane.add(new ReminderPane(), new GridBagConstraints(0, i, 1, 1, 0.0, 0.0, NORTH, HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        }
        contentPane.add(Box.createVerticalGlue(), new GridBagConstraints(0, 50, 1, 1, 1.0, 1.0, NORTH, BOTH, new Insets(0, 0, 0, 0), 0, 0));

        JScrollPane result = new JScrollPane(contentPane);
        result.setBorder(BorderFactory.createEmptyBorder());
        return result;
    }

    private Component createLeftPane() {
        JPanel result = new JPanel(new BorderLayout());
        result.add(createLeftCaption(), BorderLayout.NORTH);
        result.add(createLeftContent(), BorderLayout.CENTER);
        return result;
    }

    private Component createLeftContent() {
        JPanel result = new JPanel(new BorderLayout());
        result.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        result.add(createPipelinesPane(), BorderLayout.CENTER);
        return result;
    }

    private Component createPipelinesPane() {
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new GridBagLayout());
        for (int i = 0; i < 10; i++) {
            contentPane.add(new PipelinePane(), new GridBagConstraints(0, i, 1, 1, 0.0, 0.0, NORTH, HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        }
        contentPane.add(Box.createVerticalGlue(), new GridBagConstraints(0, 10, 1, 1, 1.0, 1.0, NORTH, BOTH, new Insets(0, 0, 0, 0), 0, 0));

        JScrollPane result = new JScrollPane(contentPane);
        result.setBorder(BorderFactory.createEmptyBorder());
        return result;
    }

    private Component createLeftCaption() {
        JLabel label = new JLabel("Customers");
        label.setFont(label.getFont().deriveFont(20.0f));

        JPanel result = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        result.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 0));
        result.add(label);
        result.add(new JButton("New Customer"));
        return result;
    }
}
