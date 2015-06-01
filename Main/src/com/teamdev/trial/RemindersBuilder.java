package com.teamdev.trial;

import com.teamdev.trial.data.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Vladimir Ikryanov
 */
public class RemindersBuilder {

    private final ApplicationContext context;

    public RemindersBuilder(ApplicationContext context) {
        this.context = context;
    }

    public List<Reminder> getRemindersForToday() {
        List<Reminder> result = new ArrayList<Reminder>();
        CustomersManager customersManager = context.getCustomersManager();
        List<Customer> customers = customersManager.getCustomers();
        for (Customer customer : customers) {
            if (customer.getState() == Customer.State.UNKNOWN) {
                PipelineState pipelineState = customer.getPipelineState();
                Date startDate = pipelineState.getStartDate();
                Date today = new Date();
                List<PhaseState> phaseStates = pipelineState.getPhaseStates();
                for (int i = 0; i < phaseStates.size(); i++) {
                    PhaseState phaseState = phaseStates.get(i);
                    if (phaseState.getState() == PhaseState.State.OPENED) {
                        long dateDiffInDays = getDateDiff(startDate, today, TimeUnit.DAYS);
                        Phase phase = context.getPhasesManager().getPhaseById(phaseState.getPhaseId());
                        int offsetInDays = phaseState.getCustomOffsetInDays();
                        if (offsetInDays == 0) {
                            offsetInDays = phase.getOffsetInDays();
                        }
                        boolean reachedOffsetInDays = dateDiffInDays >= offsetInDays;
                        if (reachedOffsetInDays) {
                            EmailTemplatesManager emailTemplatesManager = context.getEmailTemplatesManager();
                            EmailTemplate emailTemplate = emailTemplatesManager.getEmailTemplateById(phase.getEmailTemplateId());
                            boolean hasEmailTemplate = emailTemplate != null;
                            if (hasEmailTemplate) {
                                result.add(new EmailReminder(customer, phaseState, dateDiffInDays, emailTemplate));
                            }

                            boolean isFinishPhase = i == phaseStates.size() - 1;
                            if (isFinishPhase) {
                                result.add(new FinishReminder(customer, phaseState, dateDiffInDays));
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    private static long getDateDiff(Date dateOne, Date dateTwo, TimeUnit timeUnit) {
        long diffInMilliseconds = dateTwo.getTime() - dateOne.getTime();
        return timeUnit.convert(diffInMilliseconds, TimeUnit.MILLISECONDS);
    }
}
