package com.teamdev.trial;

import com.teamdev.trial.data.Customer;
import com.teamdev.trial.data.Phase;

/**
 * @author Vladimir Ikryanov
 */
public class Reminder {

    private final Customer customer;
    private final Phase phase;
    private final long expirationInDays;

    protected Reminder(Customer customer, Phase phase, long expirationInDays) {
        this.customer = customer;
        this.phase = phase;
        this.expirationInDays = expirationInDays;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Phase getPhase() {
        return phase;
    }

    public long getExpirationInDays() {
        return expirationInDays;
    }
}
