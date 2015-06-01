package com.teamdev.trial;

import com.teamdev.trial.data.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Vladimir Ikryanov
 */
public class ApplicationFrame extends JFrame {

    private final ApplicationContext context;

    public ApplicationFrame(final ApplicationContext context) throws HeadlessException {
        this.context = context;
        setContentPane(createSplitPane());
        addWindowListener(new MyWindowAdapter(context));
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

    private Component createRightCaption() {
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
        return new RemindersPane(context);
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
        result.add(createCustomersPane(), BorderLayout.CENTER);
        return result;
    }

    private Component createCustomersPane() {
        return new CustomersPane(context);
    }

    private Component createLeftCaption() {
        JLabel label = new JLabel("Customers");
        label.setFont(label.getFont().deriveFont(20.0f));

        JPanel result = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        result.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 0));
        result.add(label);
        JButton button = new JButton("New Customer");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CustomerDialog dialog = new CustomerDialog(ApplicationFrame.this, context);
                dialog.pack();
                dialog.setResizable(false);
                dialog.setLocationRelativeTo(ApplicationFrame.this);
                dialog.setVisible(true);

                Customer customer = dialog.getCustomer();
                if (customer != null) {
                    context.getCustomersManager().addCustomer(customer);
                }
            }
        });
        result.add(button);
        return result;
    }

    private static class MyWindowAdapter extends WindowAdapter {

        private final ApplicationContext context;

        private MyWindowAdapter(ApplicationContext context) {
            this.context = context;
        }

        @Override
        public void windowClosing(WindowEvent e) {
            context.save();
        }

        @Override
        public void windowDeactivated(WindowEvent e) {
            context.save();
        }
    }
}
