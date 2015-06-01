package com.teamdev.trial;

import com.teamdev.trial.data.Customer;
import com.teamdev.trial.data.Phase;

/**
 * @author Vladimir Ikryanov
 */
public class Reminder {

    private final Customer customer;
    private final Phase phase;

    protected Reminder(Customer customer, Phase phase) {
        this.customer = customer;
        this.phase = phase;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Phase getPhase() {
        return phase;
    }
}
