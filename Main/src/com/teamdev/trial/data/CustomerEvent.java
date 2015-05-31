package com.teamdev.trial.data;

/**
 * @author Vladimir Ikryanov
 */
public class CustomerEvent {

    private final Customer customer;

    public CustomerEvent(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }
}
