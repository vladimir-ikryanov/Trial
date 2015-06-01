package com.teamdev.trial;

import com.teamdev.trial.data.Customer;
import com.teamdev.trial.data.PhaseState;

/**
 * @author Vladimir Ikryanov
 */
public class FinishReminder extends Reminder {

    public FinishReminder(Customer customer, PhaseState phaseState, long expirationInDays) {
        super(customer, phaseState, expirationInDays);
    }
}
