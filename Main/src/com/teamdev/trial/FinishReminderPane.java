package com.teamdev.trial;

import com.teamdev.trial.data.Customer;
import com.teamdev.trial.data.PhaseState;
import com.teamdev.trial.ui.ButtonLabel;

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

    public FinishReminderPane(final FinishReminder reminder) {
        this.reminder = reminder;

        setOpaque(false);
        setLayout(new GridBagLayout());
        add(createInfoPane(), new GridBagConstraints(
                0, 0, 1, 1, 1.0, 0.0, WEST, HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        add(createWinButton(), new GridBagConstraints(
                1, 0, 1, 1, 0.0, 0.0, WEST, NONE, new Insets(0, 10, 0, 5), 0, 0));
        add(createLossButton(), new GridBagConstraints(
                2, 0, 1, 1, 0.0, 0.0, WEST, NONE, new Insets(0, 5, 0, 10), 0, 0));
    }

    private ButtonLabel createLossButton() {
        ButtonLabel cancelButton = new ButtonLabel("Loss", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reminder.getPhaseState().setState(PhaseState.State.CANCELED);
                reminder.getCustomer().setState(Customer.State.LOSS);
            }
        });
        cancelButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return cancelButton;
    }

    private ButtonLabel createWinButton() {
        ButtonLabel okButton = new ButtonLabel("Win", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reminder.getPhaseState().setState(PhaseState.State.CLOSED);
                reminder.getCustomer().setState(Customer.State.WIN);
            }
        });
        okButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return okButton;
    }

    private Component createInfoPane() {
        Customer customer = reminder.getCustomer();

        JLabel firstLastNameLabel = new JLabel(customer.getFirstName() + ' ' + customer.getLastName());
        firstLastNameLabel.setForeground(Color.LIGHT_GRAY);

        JLabel taskLabel = new JLabel("Make a decision");
        taskLabel.setForeground(Color.WHITE);
        taskLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
        if (reminder.getExpirationInDays() > 0) {
            taskLabel.setForeground(new Color(255, 100, 100));
            taskLabel.setToolTipText("Should have been done " + reminder.getExpirationInDays() + " days ago.");
        }

        JPanel result = new JPanel(new BorderLayout());
        result.setOpaque(false);
        result.add(taskLabel, BorderLayout.CENTER);
        result.add(firstLastNameLabel, BorderLayout.SOUTH);
        return result;
    }
}
