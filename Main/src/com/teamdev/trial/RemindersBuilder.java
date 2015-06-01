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
                Pipeline pipeline = customer.getPipeline();
                Date startDate = pipeline.getStartDate();
                Date today = new Date();
                List<Phase> phases = pipeline.getPhases();
                for (int i = 0; i < phases.size(); i++) {
                    Phase phase = phases.get(i);
                    if (phase.getState() == Phase.State.OPENED) {
                        long dateDiffInDays = getDateDiff(startDate, today, TimeUnit.DAYS);
                        boolean reachedOffsetInDays = dateDiffInDays >= phase.getOffsetInDays();
                        if (reachedOffsetInDays) {
                            EmailTemplates emailTemplates = context.getEmailTemplates();
                            EmailTemplate emailTemplate = emailTemplates.getEmailTemplateById(phase.getEmailId());
                            boolean hasEmailTemplate = emailTemplate != null;
                            if (hasEmailTemplate) {
                                result.add(new EmailReminder(customer, phase, dateDiffInDays, emailTemplate));
                            }

                            boolean isFinishPhase = i == phases.size() - 1;
                            if (isFinishPhase) {
                                result.add(new FinishReminder(customer, phase, dateDiffInDays));
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
