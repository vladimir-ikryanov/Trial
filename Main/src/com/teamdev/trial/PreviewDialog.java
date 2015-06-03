package com.teamdev.trial;

import com.teamdev.trial.data.Customer;
import com.teamdev.trial.data.EmailTemplate;
import com.teamdev.trial.ui.LightScrollPane;
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
    private final JEditorPane bodyTextArea;
    private final JTextField subjectTextField;

    private Status status;

    public PreviewDialog(Window parent, Customer customer, EmailTemplate template) {
        super(parent, ModalityType.APPLICATION_MODAL);

        setTitle("Preview");

        this.status = Status.CANCELED;
        this.customer = customer;
        this.template = template;

        subjectTextField = new JTextField();
        bodyTextArea = new JEditorPane();
        bodyTextArea.setContentType("text/html");
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
        LightScrollPane scrollPane = new LightScrollPane(bodyTextArea);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        JPanel result = new JPanel(new BorderLayout());
        result.setOpaque(false);
        result.add(scrollPane, BorderLayout.CENTER);
        result.setBorder(BorderFactory.createEmptyBorder(0, 35, 20, 35));
        return result;
    }

    private Component createCaptionPane() {
        JLabel toLabel = new JLabel("To");
        toLabel.setForeground(Color.GRAY);

        JLabel ccLabel = new JLabel("Cc");
        ccLabel.setForeground(Color.GRAY);

        JTextField toTextField = new JTextField(customer.getEmail());
        toTextField.setOpaque(false);
        toTextField.setEditable(false);
        toTextField.setBorder(BorderFactory.createEmptyBorder());

        JTextField ccTextField = new JTextField("jxbrowser-evaluation@teamdev.com");
        ccTextField.setOpaque(false);
        ccTextField.setEditable(false);
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
        template.setBody("<p>Hi {firstName},</p>" +
                "<p>The evaluation license of your JxBrowser is expiring tomorrow, and I hope you had a chance to test the library’s functionality.</p>" +
                "<p>In order to purchase the Development License please use the following <a href='https://secure.avangate.com/order/checkout.php?PRODS=1173723&QTY=1&CART=1&CARD=1&CLEAN_CART=ALL&_ga=1.69915216.1311684333.1432127743'>link</a>.<br>If another <a href='http://www.teamdev.com/jxbrowser#licensing-pricing'>type of licence</a> is required for your project, or in case you have licensing questions or need purchasing assistance, please e-mail me or our Sales team at <a href='mailto:sales@teamdev.com'>sales@teamdev.com</a>.</p><p>If you need more time to complete your evaluation, please let me know.</p><p>All the best,</p><table cellpadding='0' border='0'>" +
                "<tbody><tr><th><a href='https://plus.google.com/u/0/113918825515210809679/posts' style='text-decoration:none;'>" +
                "<img alt='Vladimir Ikryanov' src='http://www.teamdev.com/img/evaluate-email/vladimir-ikryanov.jpg' width='60'/>" +
                "</a></th><td width='10'>&nbsp;</td><td style='line-height:1.4;'>" +
                "<a href='https://plus.google.com/u/0/113918825515210809679/posts' style='text-decoration:none;color:#000;'>" +
                "<strong>Vladimir Ikryanov</strong><br>" +
                "<span style='color:#82898B;'>JxBrowser Team</span><br>" +
                "<span style='color:#82898B;'>TeamDev Ltd.</span>" +
                "</a></td></tr></tbody></table>");
        System.out.println(template.getBody());

        PreviewDialog dialog = new PreviewDialog(null, customer, template);
        dialog.setSize(800, 600);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

        String body = dialog.getBody();
        System.out.println("body = " + body);
    }
}
