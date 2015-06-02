package com.teamdev.trial;

import com.teamdev.trial.data.Customer;

import javax.swing.*;
import java.awt.*;

import static java.awt.GridBagConstraints.HORIZONTAL;
import static java.awt.GridBagConstraints.WEST;

/**
 * @author Vladimir Ikryanov
 */
public class CustomerInfoPane extends JPanel {

    public CustomerInfoPane(Customer customer) {
        JTextField firstLastNameTextField = new JTextField(customer.getFirstName() + ' ' + customer.getLastName());
        firstLastNameTextField.setOpaque(false);
        firstLastNameTextField.setEditable(false);
        firstLastNameTextField.setBorder(BorderFactory.createEmptyBorder());
        firstLastNameTextField.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
        firstLastNameTextField.setForeground(Color.WHITE);

        JTextField emailTextField = new JTextField(customer.getEmail());
        emailTextField.setOpaque(false);
        emailTextField.setEditable(false);
        emailTextField.setBorder(BorderFactory.createEmptyBorder());
        emailTextField.setForeground(Color.GRAY);

        setOpaque(false);
        setLayout(new GridBagLayout());
        add(firstLastNameTextField, new GridBagConstraints(
                0, 0, 1, 1, 1.0, 0.0, WEST, HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        add(emailTextField, new GridBagConstraints(
                0, 1, 1, 1, 1.0, 0.0, WEST, HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
    }
}
