package com.teamdev.trial;

import com.teamdev.trial.data.Customer;
import com.teamdev.trial.data.Phase;

/**
 * @author Vladimir Ikryanov
 */
public class FinishReminder extends Reminder {

    public FinishReminder(Customer customer, Phase phase) {
        super(customer, phase);
    }
}
