package com.teamdev.trial;

import com.teamdev.trial.data.*;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import static java.awt.GridBagConstraints.*;

/**
 * @author Vladimir Ikryanov
 */
public class CustomerDialog extends JDialog {

    private final ApplicationContext context;
    private Customer customer;

    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JTextField emailTextField;
    private JDateChooser registrationDateChooser;
    private JComboBox pipelineComboBox;

    public CustomerDialog(Window parent, ApplicationContext context) {
        this(parent, context, null);
    }

    public CustomerDialog(Window parent, ApplicationContext context, Customer customer) {
        super(parent, customer != null ? "Edit Customer" : "New Customer", ModalityType.APPLICATION_MODAL);
        this.context = context;
        this.customer = customer;
        setContentPane(createContentPane());
        setSize(300, 300);
        setResizable(false);
        setLocationRelativeTo(parent);
    }

    private JPanel createContentPane() {
        firstNameTextField = new JTextField(customer != null ? customer.getFirstName() : "");
        lastNameTextField = new JTextField(customer != null ? customer.getLastName() : "");
        emailTextField = new JTextField(customer != null ? customer.getEmail() : "");
        registrationDateChooser = new JDateChooser(customer != null ? customer.getPipelineState().getStartDate() : new Date());
        PipelinesManager pipelinesManager = context.getPipelinesManager();
        List<Pipeline> pipelines = pipelinesManager.getPipelines();
        pipelineComboBox = new JComboBox(pipelines.toArray());
        pipelineComboBox.setOpaque(false);
        if (customer != null) {
            pipelineComboBox.setSelectedItem(pipelinesManager.getPipelineById(customer.getPipelineState().getPipelineId()));
        }

        firstNameTextField.getDocument().addDocumentListener(new FirstNameDocumentListener());

        JPanel contentPane = new JPanel(new GridBagLayout());
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPane.add(new JLabel("First Name:"), new GridBagConstraints(
                0, 0, 1, 1, 1.0, 0.0, NORTH, HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        contentPane.add(firstNameTextField, new GridBagConstraints(
                0, 1, 1, 1, 1.0, 0.0, NORTH, HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

        contentPane.add(new JLabel("Last Name:"), new GridBagConstraints(
                0, 2, 1, 1, 1.0, 0.0, NORTH, HORIZONTAL, new Insets(5, 0, 0, 0), 0, 0));
        contentPane.add(lastNameTextField, new GridBagConstraints(
                0, 3, 1, 1, 1.0, 0.0, NORTH, HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

        contentPane.add(new JLabel("Email:"), new GridBagConstraints(
                0, 4, 1, 1, 1.0, 0.0, NORTH, HORIZONTAL, new Insets(5, 0, 0, 0), 0, 0));
        contentPane.add(emailTextField, new GridBagConstraints(
                0, 5, 1, 1, 1.0, 0.0, NORTH, HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

        contentPane.add(new JLabel("Registration Date:"), new GridBagConstraints(
                0, 6, 1, 1, 1.0, 0.0, NORTH, HORIZONTAL, new Insets(5, 0, 0, 0), 0, 0));
        contentPane.add(registrationDateChooser, new GridBagConstraints(
                0, 7, 1, 1, 1.0, 0.0, NORTH, HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

        contentPane.add(new JLabel("Pipeline:"), new GridBagConstraints(
                0, 8, 1, 1, 1.0, 0.0, NORTH, HORIZONTAL, new Insets(5, 0, 0, 0), 0, 0));
        contentPane.add(pipelineComboBox, new GridBagConstraints(
                0, 9, 1, 1, 1.0, 0.0, NORTH, HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

        contentPane.add(Box.createVerticalGlue(), new GridBagConstraints(
                0, 10, 1, 1, 1.0, 1.0, NORTH, BOTH, new Insets(0, 0, 0, 0), 0, 0));

        contentPane.add(createActionPane(), new GridBagConstraints(
                0, 11, 1, 1, 1.0, 0.0, NORTH, HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        return contentPane;
    }

    private JPanel createActionPane() {
        JPanel actionPane = new JPanel(new FlowLayout());
        actionPane.add(createCreateButton());
        actionPane.add(createCancelButton());
        return actionPane;
    }

    private JButton createCancelButton() {
        JButton result = new JButton("Cancel");
        result.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customer = null;
                dispose();
            }
        });
        return result;
    }

    private JButton createCreateButton() {
        JButton result = new JButton(customer == null ? "Create" : "Save");
        result.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameTextField.getText();
                String lastName = lastNameTextField.getText();
                String email = emailTextField.getText();
                Date date = registrationDateChooser.getDate();
                if (!firstName.isEmpty() && !email.isEmpty()) {
                    if (customer == null) {
                        customer = new Customer();
                        customer.setState(Customer.State.UNKNOWN);
                    }
                    customer.setFirstName(firstName);
                    customer.setLastName(lastName);
                    customer.setEmail(email);

                    Pipeline pipeline = (Pipeline) pipelineComboBox.getSelectedItem();
                    PipelineState pipelineState = PipelineStateFactory.create(date, pipeline);
                    customer.setPipelineState(pipelineState);
                    dispose();
                }
            }
        });
        return result;
    }

    public Customer getCustomer() {
        return customer;
    }

    public static void main(String[] args) {
        CustomerDialog dialog = new CustomerDialog(null, new ApplicationContext(new ApplicationSettings()));
        dialog.setResizable(false);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

        System.out.println("dialog.getCustomer() = " + dialog.getCustomer());
    }

    private class FirstNameDocumentListener implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent documentEvent) {
            processText(firstNameTextField.getText());
        }

        @Override
        public void removeUpdate(DocumentEvent documentEvent) {
            processText(firstNameTextField.getText());
        }

        @Override
        public void changedUpdate(DocumentEvent documentEvent) {
            processText(firstNameTextField.getText());
        }

        private void processText(final String text) {
            if (text.contains(" ") && lastNameTextField.getText().isEmpty()) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        StringTokenizer tokenizer = new StringTokenizer(text, " ");
                        firstNameTextField.setText(tokenizer.nextToken());
                        if (tokenizer.hasMoreTokens()) {
                            lastNameTextField.setText(tokenizer.nextToken());
                        }
                    }
                });
            }
        }
    }
}
