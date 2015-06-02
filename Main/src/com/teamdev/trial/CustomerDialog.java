package com.teamdev.trial;

import com.teamdev.trial.data.Customer;
import com.teamdev.trial.data.Pipeline;
import com.teamdev.trial.data.PipelineState;
import com.teamdev.trial.data.PipelineStateFactory;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

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

    public CustomerDialog(Frame parent, ApplicationContext context) {
        super(parent, "New Customer", true);
        this.context = context;
        setContentPane(createContentPane());
        setSize(300, 300);
    }

    private JPanel createContentPane() {
        firstNameTextField = new JTextField();
        lastNameTextField = new JTextField();
        emailTextField = new JTextField();
        registrationDateChooser = new JDateChooser(new Date());
        List<Pipeline> pipelines = context.getPipelinesManager().getPipelines();
        pipelineComboBox = new JComboBox(pipelines.toArray());
        pipelineComboBox.setOpaque(false);

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
        JButton result = new JButton("Create");
        result.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameTextField.getText();
                String lastName = lastNameTextField.getText();
                String email = emailTextField.getText();
                Date date = registrationDateChooser.getDate();
                if (!firstName.isEmpty() && !email.isEmpty()) {
                    customer = new Customer();
                    customer.setFirstName(firstName);
                    customer.setLastName(lastName);
                    customer.setEmail(email);
                    customer.setState(Customer.State.UNKNOWN);

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
        CustomerDialog dialog = new CustomerDialog(null, null);
        dialog.setResizable(false);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

        System.out.println("dialog.getCustomer() = " + dialog.getCustomer());
    }
}
