package com.teamdev.trial;

import com.teamdev.trial.data.Customer;
import com.teamdev.trial.data.EmailTemplate;
import com.teamdev.trial.data.PhaseState;

/**
 * @author Vladimir Ikryanov
 */
public class EmailReminder extends Reminder {

    private final EmailTemplate emailTemplate;

    public EmailReminder(Customer customer, PhaseState phaseState, long expirationInDays, EmailTemplate emailTemplate) {
        super(customer, phaseState, expirationInDays);
        this.emailTemplate = emailTemplate;
    }

    public EmailTemplate getEmailTemplate() {
        return emailTemplate;
    }
}
