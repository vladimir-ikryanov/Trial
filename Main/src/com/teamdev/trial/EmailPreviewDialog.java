package com.teamdev.trial;

import com.teamdev.trial.data.Customer;
import com.teamdev.trial.data.EmailTemplate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Vladimir Ikryanov
 */
public class EmailPreviewDialog extends JDialog {

    public enum Status {
        SENT, CANCELED
    }

    private final Customer customer;
    private final EmailTemplate template;
    private final JTextArea bodyTextArea;
    private final JTextField subjectTextField;

    private Status status;

    public EmailPreviewDialog(Window parent, Customer customer, EmailTemplate template) {
        super(parent, ModalityType.APPLICATION_MODAL);
        this.customer = customer;
        this.template = template;

        subjectTextField = new JTextField();
        bodyTextArea = new JTextArea();

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.add(createSubjectPane(), BorderLayout.NORTH);
        contentPane.add(createBodyPane(), BorderLayout.CENTER);
        contentPane.add(createActionsPane(), BorderLayout.SOUTH);
        setContentPane(contentPane);
    }

    private Component createActionsPane() {
        JPanel result = new JPanel(new FlowLayout(FlowLayout.CENTER));
        result.add(createSendButton());
        result.add(createCancelButton());
        return result;
    }

    private JButton createSendButton() {
        JButton button = new JButton("Send");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                status = Status.SENT;
                dispose();
            }
        });
        return button;
    }

    private JButton createCancelButton() {
        JButton button = new JButton("Cancel");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                status = Status.CANCELED;
                dispose();
            }
        });
        return button;
    }

    private Component createBodyPane() {
        String updatedBody = template.getBody().replace("{firstName}", customer.getFirstName());
        bodyTextArea.setText(updatedBody);
        return new JScrollPane(bodyTextArea);
    }

    private Component createSubjectPane() {
        subjectTextField.setText(template.getSubject());
        return subjectTextField;
    }

    public String getSubject() {
        return subjectTextField.getText();
    }

    public String getBody() {
        return bodyTextArea.getText();
    }

    public Status getStatus() {
        return status;
    }
}
