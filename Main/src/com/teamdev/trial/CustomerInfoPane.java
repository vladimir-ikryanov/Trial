package com.teamdev.trial;

import com.teamdev.trial.data.Customer;
import com.teamdev.trial.ui.ButtonLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.GridBagConstraints.HORIZONTAL;
import static java.awt.GridBagConstraints.WEST;

/**
 * @author Vladimir Ikryanov
 */
public class CustomerInfoPane extends JPanel {

    private final ApplicationContext context;
    private final Customer customer;

    public CustomerInfoPane(ApplicationContext context, Customer customer) {
        this.context = context;
        this.customer = customer;
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
        emailTextField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        setOpaque(false);
        setLayout(new GridBagLayout());
        add(firstLastNameTextField, new GridBagConstraints(
                0, 0, 1, 1, 1.0, 0.0, WEST, HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        add(emailTextField, new GridBagConstraints(
                0, 1, 1, 1, 1.0, 0.0, WEST, HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        add(createActionsPane(), new GridBagConstraints(
                0, 2, 1, 1, 1.0, 0.0, WEST, HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
    }

    private Component createActionsPane() {
        JPanel result = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        result.setOpaque(false);
        result.add(createWinButton());
        result.add(Box.createHorizontalStrut(5));
        result.add(createLossButton());
        result.add(Box.createHorizontalStrut(5));
        result.add(createEditButton());
        result.add(Box.createHorizontalStrut(5));
        result.add(createRemoveButton());
        return result;
    }

    private Component createEditButton() {
        ButtonLabel button = new ButtonLabel("Edit", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window window = SwingUtilities.getWindowAncestor(CustomerInfoPane.this);
                CustomerDialog dialog = new CustomerDialog(window, context, customer);
                dialog.setVisible(true);
                Customer customer = dialog.getCustomer();
                if (customer != null) {
                    context.getCustomersManager().addCustomer(customer);
                }
            }
        });
        return button;
    }

    private Component createRemoveButton() {
        ButtonLabel button = new ButtonLabel("Remove", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                context.getCustomersManager().removeCustomer(customer);
            }
        });
        return button;
    }

    private Component createLossButton() {
        ButtonLabel result = new ButtonLabel("Loss", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customer.setState(Customer.State.LOSS);
            }
        });
        result.setForeground(new Color(255, 100, 100));
        return result;
    }

    private Component createWinButton() {
        ButtonLabel result = new ButtonLabel("Win", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customer.setState(Customer.State.WIN);
            }
        });
        result.setForeground(new Color(0, 128, 64));
        return result;
    }
}
