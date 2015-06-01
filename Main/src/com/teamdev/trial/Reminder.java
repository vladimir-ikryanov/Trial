package com.teamdev.trial;

import com.teamdev.trial.data.Customer;
import com.teamdev.trial.data.PhaseState;

/**
 * @author Vladimir Ikryanov
 */
public class Reminder {

    private final Customer customer;
    private final PhaseState phaseState;
    private final long expirationInDays;

    protected Reminder(Customer customer, PhaseState phaseState, long expirationInDays) {
        this.customer = customer;
        this.phaseState = phaseState;
        this.expirationInDays = expirationInDays;
    }

    public Customer getCustomer() {
        return customer;
    }

    public PhaseState getPhaseState() {
        return phaseState;
    }

    public long getExpirationInDays() {
        return expirationInDays;
    }
}
