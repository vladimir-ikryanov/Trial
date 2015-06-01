package com.teamdev.trial;

import com.google.gson.reflect.TypeToken;
import com.teamdev.trial.data.*;

import java.util.List;

/**
 * @author Vladimir Ikryanov
 */
public class ApplicationContext {

    private final EmailTemplates emailTemplates;
    private final CustomersManager customersManager;
    private final JSONDataStorage<List<Customer>> customersDataStorage;
    private final JSONDataStorage<List<EmailTemplate>> emailTemplatesDataStorage;

    public ApplicationContext(ApplicationSettings settings) {
        this.emailTemplates = new EmailTemplates();
        this.customersManager = new CustomersManager();
        this.customersDataStorage = new JSONDataStorage<List<Customer>>(settings.getCustomersFile(),
                new TypeToken<List<Customer>>() {});
        this.emailTemplatesDataStorage = new JSONDataStorage<List<EmailTemplate>>(settings.getEmailTemplatesFile(),
                new TypeToken<List<EmailTemplate>>() {});
    }

    public CustomersManager getCustomersManager() {
        return customersManager;
    }

    public EmailTemplates getEmailTemplates() {
        return emailTemplates;
    }

    public void load() throws Exception {
        List<Customer> customers = customersDataStorage.load();
        if (customers != null) {
            for (Customer customer : customers) {
                customersManager.addCustomer(customer);
            }
        }

        List<EmailTemplate> templates = emailTemplatesDataStorage.load();
        if (templates != null) {
            for (EmailTemplate template : templates) {
                emailTemplates.addEmailTemplate(template);
            }
        }
    }

    public void save() throws Exception {
        customersDataStorage.save(customersManager.getCustomers());
    }
}
