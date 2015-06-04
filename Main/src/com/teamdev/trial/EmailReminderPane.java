package com.teamdev.trial;

import com.teamdev.trial.data.Customer;
import com.teamdev.trial.data.Phase;
import com.teamdev.trial.data.PhaseState;
import com.teamdev.trial.ui.ButtonLabel;

import javax.mail.MessagingException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.GridBagConstraints.*;

/**
 * @author Vladimir Ikryanov
 */
public class EmailReminderPane extends JPanel {

    private final EmailReminder reminder;
    private final ApplicationContext context;

    public EmailReminderPane(final ApplicationContext context, final EmailReminder reminder) {
        this.context = context;
        this.reminder = reminder;

        setOpaque(false);
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        add(createInfoPane(), new GridBagConstraints(
                0, 0, 1, 1, 1.0, 0.0, WEST, HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        add(createSendButton(), new GridBagConstraints(
                1, 0, 1, 1, 0.0, 0.0, WEST, NONE, new Insets(0, 10, 0, 5), 0, 0));
        add(createCancelButton(), new GridBagConstraints(
                2, 0, 1, 1, 0.0, 0.0, WEST, NONE, new Insets(0, 5, 0, 10), 0, 0));
    }

    private Component createCancelButton() {
        ButtonLabel result = new ButtonLabel("Cancel", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reminder.getPhaseState().setState(PhaseState.State.CANCELED);
                reminder.getCustomer().setState(Customer.State.UNKNOWN);
            }
        });
        result.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return result;
    }

    private Component createSendButton() {
        ButtonLabel result = new ButtonLabel("Send", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window parent = SwingUtilities.getWindowAncestor(EmailReminderPane.this);
                PreviewDialog dialog = new PreviewDialog(parent, reminder.getCustomer(), reminder.getEmailTemplate());
                dialog.setSize(800, 600);
                dialog.setLocationRelativeTo(parent);
                dialog.setVisible(true);

                if (dialog.getStatus() == PreviewDialog.Status.SENT) {
                    String to = reminder.getCustomer().getEmail();
                    String subject = dialog.getSubject();
                    String body = dialog.getBody();
                    try {
                        ApplicationSettings settings = context.getSettings();
                        String from = settings.getFrom();
                        String cc = settings.getCC();
                        EmailService.send(to, from, cc, "", subject, body);
                    } catch (MessagingException exception) {
                        throw new RuntimeException(exception);
                    }
                    reminder.getPhaseState().setState(PhaseState.State.CLOSED);
                    reminder.getCustomer().setState(Customer.State.UNKNOWN);
                }
            }
        });
        result.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return result;
    }

    private Component createInfoPane() {
        Customer customer = reminder.getCustomer();

        JLabel firstLastNameLabel = new JLabel(customer.getFirstName() + ' ' + customer.getLastName());
        firstLastNameLabel.setForeground(Color.LIGHT_GRAY);

        PhaseState phaseState = reminder.getPhaseState();
        Phase phase = context.getPhasesManager().getPhaseById(phaseState.getPhaseId());
        JLabel taskLabel = new JLabel("Send " + phase.getName() + " email");
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
