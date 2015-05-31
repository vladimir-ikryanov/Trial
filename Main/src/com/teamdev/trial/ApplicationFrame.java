package com.teamdev.trial;

import com.teamdev.trial.data.Customer;
import com.teamdev.trial.data.PipelineFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import static java.awt.GridBagConstraints.*;

/**
 * @author Vladimir Ikryanov
 */
public class ApplicationFrame extends JFrame {

    private final ApplicationContext context;

    public ApplicationFrame(ApplicationContext context) throws HeadlessException {
        this.context = context;
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
        result.add(createCustomersPane(), BorderLayout.CENTER);
        return result;
    }

    private Component createCustomersPane() {
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new GridBagLayout());
        List<Customer> customers = context.getCustomers().getCustomers();
        for (int i = 0; i < customers.size(); i++) {
            contentPane.add(new CustomerPane(customers.get(i)), new GridBagConstraints(0, i, 1, 1, 0.0, 0.0, NORTH, HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        }
        contentPane.add(Box.createVerticalGlue(), new GridBagConstraints(0, customers.size(), 1, 1, 1.0, 1.0, NORTH, BOTH, new Insets(0, 0, 0, 0), 0, 0));

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
        JButton button = new JButton("New Customer");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Customer customer = new Customer("Manish", "Bla", "b@gmail.com", PipelineFactory.create30DaysEvaluation(new Date()), Customer.State.UNKNOWN);
                context.getCustomers().addCustomer(customer);
            }
        });
        result.add(button);
        return result;
    }
}
