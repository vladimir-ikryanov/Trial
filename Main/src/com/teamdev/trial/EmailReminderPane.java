package com.teamdev.trial;

import com.teamdev.trial.data.Customer;
import com.teamdev.trial.data.Phase;
import com.teamdev.trial.data.PhaseState;

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

        setLayout(new GridBagLayout());
        add(createInfoPane(), new GridBagConstraints(
                0, 0, 1, 1, 1.0, 0.0, WEST, HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        JButton okButton = new JButton("Send");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window parent = SwingUtilities.getWindowAncestor(EmailReminderPane.this);
                EmailPreviewDialog dialog = new EmailPreviewDialog(parent, reminder.getCustomer(), reminder.getEmailTemplate());
                dialog.setSize(800, 600);
                dialog.setLocationRelativeTo(parent);
                dialog.setVisible(true);

                if (dialog.getStatus() == EmailPreviewDialog.Status.SENT) {
                    String to = reminder.getCustomer().getEmail();
                    String subject = dialog.getSubject();
                    String body = dialog.getBody();
                    try {
                        ApplicationSettings settings = context.getSettings();
                        String from = settings.getFrom();
                        String cc = settings.getCC();
                        EmailService.send(to, from, cc, "password", subject, body);
                    } catch (MessagingException exception) {
                        throw new RuntimeException(exception);
                    }
                    reminder.getPhaseState().setState(PhaseState.State.CLOSED);
                    reminder.getCustomer().setState(Customer.State.UNKNOWN);
                }
            }
        });
        add(okButton, new GridBagConstraints(
                1, 0, 1, 1, 0.0, 0.0, WEST, NONE, new Insets(0, 0, 0, 0), 0, 0));
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reminder.getPhaseState().setState(PhaseState.State.CANCELED);
                reminder.getCustomer().setState(Customer.State.UNKNOWN);
            }
        });
        add(cancelButton, new GridBagConstraints(
                2, 0, 1, 1, 0.0, 0.0, WEST, NONE, new Insets(0, 0, 0, 0), 0, 0));
    }

    private Component createInfoPane() {
        Customer customer = reminder.getCustomer();
        JPanel result = new JPanel(new BorderLayout());
        result.add(new JLabel(customer.getFirstName() + ' ' + customer.getLastName()), BorderLayout.CENTER);
        PhaseState phaseState = reminder.getPhaseState();
        Phase phase = context.getPhasesManager().getPhaseById(phaseState.getPhaseId());
        JLabel label = new JLabel("Send " + phase.getName() + " email");
        if (reminder.getExpirationInDays() > 0) {
            label.setForeground(Color.RED);
            label.setToolTipText("Should have been done " + reminder.getExpirationInDays() + " days ago.");
        }
        result.add(label, BorderLayout.SOUTH);
        return result;
    }
}
