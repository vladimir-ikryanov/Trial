package com.teamdev.trial;

import com.teamdev.trial.data.Customer;
import com.teamdev.trial.data.CustomersManagerEvent;
import com.teamdev.trial.data.CustomersManagerListener;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static java.awt.GridBagConstraints.BOTH;
import static java.awt.GridBagConstraints.HORIZONTAL;
import static java.awt.GridBagConstraints.NORTH;

/**
 * @author Vladimir Ikryanov
 */
public class CustomersPane extends JPanel {

    private final JPanel customersPane;
    private final ApplicationContext context;
    private final CustomersManagerListener customersManagerListener;

    public CustomersPane(ApplicationContext context) {
        this.context = context;
        this.customersManagerListener = new DefaultCustomersManagerListener();
        this.customersPane = new JPanel(new GridBagLayout());
        this.customersPane.setOpaque(false);

        JScrollPane scrollPane = new JScrollPane(customersPane);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        setOpaque(false);
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        updateCustomersPane(context.getCustomersManager().getCustomers());
    }

    @Override
    public void addNotify() {
        super.addNotify();
        context.getCustomersManager().addCustomersManagerListener(customersManagerListener);
    }

    @Override
    public void removeNotify() {
        context.getCustomersManager().removeCustomersManagerListener(customersManagerListener);
        super.removeNotify();
    }

    private void updateCustomersPane(List<Customer> customers) {
        customersPane.removeAll();
        for (int i = 0; i < customers.size(); i++) {
            Customer customer = customers.get(i);
            if (customer.getState() == Customer.State.UNKNOWN) {
                customersPane.add(new CustomerPane(context, customer), new GridBagConstraints(
                        0, i, 1, 1, 0.0, 0.0, NORTH, HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
            }
        }
        customersPane.add(Box.createVerticalGlue(), new GridBagConstraints(
                0, customers.size(), 1, 1, 1.0, 1.0, NORTH, BOTH, new Insets(0, 0, 0, 0), 0, 0));
        customersPane.validate();
        customersPane.repaint();
    }

    private class DefaultCustomersManagerListener implements CustomersManagerListener {
        @Override
        public void onCustomerAdded(CustomersManagerEvent event) {
            updateCustomersPane(event.getCustomersManager().getCustomers());
        }

        @Override
        public void onCustomerRemoved(CustomersManagerEvent event) {
            updateCustomersPane(event.getCustomersManager().getCustomers());
        }

        @Override
        public void onCustomerChanged(CustomersManagerEvent event) {
            updateCustomersPane(event.getCustomersManager().getCustomers());
        }
    }
}
