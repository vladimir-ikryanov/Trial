package com.teamdev.trial;

import com.teamdev.trial.data.Customer;
import com.teamdev.trial.data.Phase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.GridBagConstraints.*;

/**
 * @author Vladimir Ikryanov
 */
public class ReminderPane extends JPanel {

    private final ApplicationContext context;
    private final Reminder reminder;

    public ReminderPane(ApplicationContext context, final Reminder reminder) {
        this.context = context;
        this.reminder = reminder;

        setLayout(new GridBagLayout());
        add(createInfoPane(), new GridBagConstraints(
                0, 0, 1, 1, 1.0, 0.0, WEST, HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reminder.getPhase().setState(Phase.State.CLOSED);
                reminder.getCustomer().fireOnCustomerChanged();
            }
        });
        add(okButton, new GridBagConstraints(
                1, 0, 1, 1, 0.0, 0.0, WEST, NONE, new Insets(0, 0, 0, 0), 0, 0));
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reminder.getPhase().setState(Phase.State.CANCELED);
                reminder.getCustomer().fireOnCustomerChanged();
            }
        });
        add(cancelButton, new GridBagConstraints(
                2, 0, 1, 1, 0.0, 0.0, WEST, NONE, new Insets(0, 0, 0, 0), 0, 0));
    }

    private Component createInfoPane() {
        Customer customer = reminder.getCustomer();
        JPanel result = new JPanel(new BorderLayout());
        result.add(new JLabel(customer.getFirstName() + ' ' + customer.getLastName()), BorderLayout.CENTER);
        result.add(new JLabel("Send " + reminder.getPhase().getName() + " email"), BorderLayout.SOUTH);
        return result;
    }
}
