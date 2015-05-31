package com.teamdev.trial.data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vladimir Ikryanov
 */
public class CustomersManager {

    private final List<Customer> customers;
    private final List<CustomersManagerListener> listeners;
    private final CustomerListener customerListener;

    public CustomersManager() {
        this.customers = new ArrayList<Customer>();
        this.listeners = new ArrayList<CustomersManagerListener>();
        this.customerListener = new DefaultCustomerListener();
    }

    public void addCustomersManagerListener(CustomersManagerListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    public void removeCustomersManagerListener(CustomersManagerListener listener) {
        listeners.remove(listener);
    }

    public List<CustomersManagerListener> getCustomersManagerListeners() {
        return new ArrayList<CustomersManagerListener>(listeners);
    }

    public List<Customer> getCustomers() {
        return new ArrayList<Customer>(customers);
    }

    public void addCustomer(Customer customer) {
        if (!customers.contains(customer)) {
            customers.add(customer);
            customer.addCustomersListener(customerListener);
            fireOnCustomerAdded(customer);
        }
    }

    public void removeCustomer(Customer customer) {
        if (customers.remove(customer)) {
            fireOnCustomerRemoved(customer);
            customer.removeCustomerListener(customerListener);
        }
    }

    private void fireOnCustomerAdded(Customer customer) {
        CustomersManagerEvent event = new CustomersManagerEvent(this, customer);
        for (CustomersManagerListener listener : getCustomersManagerListeners()) {
            listener.onCustomerAdded(event);
        }
    }

    private void fireOnCustomerChanged(Customer customer) {
        CustomersManagerEvent event = new CustomersManagerEvent(this, customer);
        for (CustomersManagerListener listener : getCustomersManagerListeners()) {
            listener.onCustomerChanged(event);
        }
    }

    private void fireOnCustomerRemoved(Customer customer) {
        CustomersManagerEvent event = new CustomersManagerEvent(this, customer);
        for (CustomersManagerListener listener : getCustomersManagerListeners()) {
            listener.onCustomerRemoved(event);
        }
    }

    private class DefaultCustomerListener implements CustomerListener {
        @Override
        public void onCustomerChanged(CustomerEvent event) {
            fireOnCustomerChanged(event.getCustomer());
        }
    }
}
