package com.teamdev.trial;

import com.teamdev.trial.data.Customer;
import com.teamdev.trial.data.Phase;

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

    public EmailReminderPane(ApplicationContext context, final EmailReminder reminder) {
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
                        EmailService.send(to, "vladimir.ikryanov@teamdev.com", "jxbrowser-evaluation@teamdev.com", "password", subject, body);
                    } catch (MessagingException exception) {
                        throw new RuntimeException(exception);
                    }
                    reminder.getPhase().setState(Phase.State.CLOSED);
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
                reminder.getPhase().setState(Phase.State.CANCELED);
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
        result.add(new JLabel("Send " + reminder.getPhase().getName() + " email"), BorderLayout.SOUTH);
        return result;
    }
}
