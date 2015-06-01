package com.teamdev.trial;

import com.teamdev.trial.data.Customer;
import com.teamdev.trial.data.PhaseState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.GridBagConstraints.*;

/**
 * @author Vladimir Ikryanov
 */
public class FinishReminderPane extends JPanel {

    private final FinishReminder reminder;
    private final ApplicationContext context;

    public FinishReminderPane(ApplicationContext context, final FinishReminder reminder) {
        this.context = context;
        this.reminder = reminder;

        setLayout(new GridBagLayout());
        add(createInfoPane(), new GridBagConstraints(
                0, 0, 1, 1, 1.0, 0.0, WEST, HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        JButton okButton = new JButton("Win");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reminder.getPhaseState().setState(PhaseState.State.CLOSED);
                reminder.getCustomer().setState(Customer.State.WIN);
            }
        });
        add(okButton, new GridBagConstraints(
                1, 0, 1, 1, 0.0, 0.0, WEST, NONE, new Insets(0, 0, 0, 0), 0, 0));
        JButton cancelButton = new JButton("Loss");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reminder.getPhaseState().setState(PhaseState.State.CANCELED);
                reminder.getCustomer().setState(Customer.State.LOSS);
            }
        });
        add(cancelButton, new GridBagConstraints(
                2, 0, 1, 1, 0.0, 0.0, WEST, NONE, new Insets(0, 0, 0, 0), 0, 0));
    }

    private Component createInfoPane() {
        Customer customer = reminder.getCustomer();
        JPanel result = new JPanel(new BorderLayout());
        result.add(new JLabel(customer.getFirstName() + ' ' + customer.getLastName()), BorderLayout.CENTER);
        result.add(new JLabel("Make a decision"), BorderLayout.SOUTH);
        return result;
    }
}
