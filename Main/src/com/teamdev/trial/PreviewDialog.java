package com.teamdev.trial;

import com.teamdev.trial.data.Customer;
import com.teamdev.trial.data.EmailTemplate;
import com.teamdev.trial.ui.WhiteButtonUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.GridBagConstraints.*;

/**
 * @author Vladimir Ikryanov
 */
public class PreviewDialog extends JDialog {

    public enum Status {
        SENT, CANCELED
    }

    private final Customer customer;
    private final EmailTemplate template;
    private final JTextArea bodyTextArea;
    private final JTextField subjectTextField;

    private Status status;

    public PreviewDialog(Window parent, Customer customer, EmailTemplate template) {
        super(parent, ModalityType.APPLICATION_MODAL);

        setTitle("Preview");

        this.status = Status.CANCELED;
        this.customer = customer;
        this.template = template;

        subjectTextField = new JTextField();
        bodyTextArea = new JTextArea();
        bodyTextArea.setFont(new Font("Arial", Font.PLAIN, 12));

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(Color.WHITE);
        contentPane.add(createCaptionPane(), BorderLayout.NORTH);
        contentPane.add(createBodyPane(), BorderLayout.CENTER);
        contentPane.add(createActionsPane(), BorderLayout.SOUTH);
        setContentPane(contentPane);
    }

    private Component createActionsPane() {
        JPanel result = new JPanel(new FlowLayout(FlowLayout.CENTER));
        result.setOpaque(false);
        result.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        result.add(createSendButton());
        return result;
    }

    private JButton createSendButton() {
        JButton button = new JButton("Send");
        button.setUI(new WhiteButtonUI());
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                status = Status.SENT;
                dispose();
            }
        });
        return button;
    }

    private Component createBodyPane() {
        String updatedBody = template.getBody().replace("{firstName}", customer.getFirstName());
        bodyTextArea.setText(updatedBody);
        bodyTextArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(bodyTextArea);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 35, 20, 35));
        return scrollPane;
    }

    private Component createCaptionPane() {
        JLabel toLabel = new JLabel("To");
        toLabel.setForeground(Color.GRAY);

        JLabel ccLabel = new JLabel("Cc");
        ccLabel.setForeground(Color.GRAY);

        JTextField toTextField = new JTextField(customer.getEmail());
        toTextField.setBorder(BorderFactory.createEmptyBorder());

        JTextField ccTextField = new JTextField("jxbrowser-evaluation@teamdev.com");
        ccTextField.setBorder(BorderFactory.createEmptyBorder());

        JPanel result = new JPanel(new GridBagLayout());
        result.setOpaque(false);
        result.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        result.add(toLabel, new GridBagConstraints(
                0, 0, 1, 1, 0.0, 0.0, EAST, NONE, new Insets(0, 0, 0, 5), 0, 0));
        result.add(toTextField, new GridBagConstraints(
                1, 0, 1, 1, 1.0, 0.0, EAST, HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

        result.add(Box.createVerticalStrut(5), new GridBagConstraints(
                0, 1, 2, 1, 1.0, 0.0, EAST, NONE, new Insets(0, 0, 0, 0), 0, 0));

        result.add(ccLabel, new GridBagConstraints(
                0, 2, 1, 1, 0.0, 0.0, EAST, NONE, new Insets(0, 0, 0, 5), 0, 0));
        result.add(ccTextField, new GridBagConstraints(
                1, 2, 1, 1, 1.0, 0.0, EAST, HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

        result.add(subjectTextField, new GridBagConstraints(
                1, 3, 1, 1, 1.0, 0.0, EAST, HORIZONTAL, new Insets(10, 0, 0, 0), 0, 0));

        subjectTextField.setText(template.getSubject());
        subjectTextField.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        subjectTextField.setBorder(BorderFactory.createEmptyBorder());

        return result;
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

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@gmail.com");

        EmailTemplate template = new EmailTemplate();
        template.setSubject("First Day of JxBrowser Evaluation");
        template.setBody("Hell {firstName},\n\nThank you for contacting Support.");

        PreviewDialog dialog = new PreviewDialog(null, customer, template);
        dialog.setSize(500, 300);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
