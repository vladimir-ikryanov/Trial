package com.teamdev.trial;

import javax.swing.*;
import java.awt.*;

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
        result.setMinimumSize(new Dimension(400, 200));
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
            contentPane.add(new ReminderPane(), new GridBagConstraints(0, i, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        }
        contentPane.add(Box.createVerticalGlue(), new GridBagConstraints(0, 50, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

        JScrollPane result = new JScrollPane(contentPane);
        result.setBorder(BorderFactory.createEmptyBorder());
        return result;
    }

    private Component createLeftPane() {
        JPanel result = new JPanel(new BorderLayout());
        result.add(createLeftCaption(), BorderLayout.NORTH);
        return result;
    }

    private JLabel createLeftCaption() {
        JLabel result = new JLabel("Customers");
        result.setFont(result.getFont().deriveFont(20.0f));
        return result;
    }
}
