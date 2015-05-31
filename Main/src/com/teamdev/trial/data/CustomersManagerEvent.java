package com.teamdev.trial.data;

/**
 * @author Vladimir Ikryanov
 */
public class CustomersManagerEvent {

    private final Customer customer;
    private final CustomersManager customersManager;

    public CustomersManagerEvent(CustomersManager customersManager, Customer customer) {
        this.customersManager = customersManager;
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public CustomersManager getCustomersManager() {
        return customersManager;
    }
}
