package com.teamdev.trial;

import com.google.gson.reflect.TypeToken;
import com.teamdev.trial.data.*;

import java.util.List;

/**
 * @author Vladimir Ikryanov
 */
public class ApplicationContext {

    private final ApplicationSettings settings;

    private final PhasesManager phasesManager;
    private final PipelinesManager pipelinesManager;
    private final CustomersManager customersManager;
    private final EmailTemplatesManager emailTemplatesManager;

    private final JSONDataStorage<List<Phase>> phasesDataStorage;
    private final JSONDataStorage<List<Pipeline>> pipelinesDataStorage;
    private final JSONDataStorage<List<Customer>> customersDataStorage;
    private final JSONDataStorage<List<EmailTemplate>> emailTemplatesDataStorage;

    public ApplicationContext(ApplicationSettings settings) {
        this.settings = settings;

        this.emailTemplatesManager = new EmailTemplatesManager();
        this.customersManager = new CustomersManager();
        this.phasesManager = new PhasesManager();
        this.pipelinesManager = new PipelinesManager();

        this.phasesDataStorage = new JSONDataStorage<List<Phase>>(settings.getPhasesFile(),
                new TypeToken<List<Phase>>() {
                });
        this.pipelinesDataStorage = new JSONDataStorage<List<Pipeline>>(settings.getPipelinesFile(),
                new TypeToken<List<Pipeline>>() {
                });
        this.customersDataStorage = new JSONDataStorage<List<Customer>>(settings.getCustomersFile(),
                new TypeToken<List<Customer>>() {
                });
        this.emailTemplatesDataStorage = new JSONDataStorage<List<EmailTemplate>>(settings.getEmailsFile(),
                new TypeToken<List<EmailTemplate>>() {
                });
    }

    public ApplicationSettings getSettings() {
        return settings;
    }

    public PipelinesManager getPipelinesManager() {
        return pipelinesManager;
    }

    public PhasesManager getPhasesManager() {
        return phasesManager;
    }

    public CustomersManager getCustomersManager() {
        return customersManager;
    }

    public EmailTemplatesManager getEmailTemplatesManager() {
        return emailTemplatesManager;
    }

    @SuppressWarnings("MethodWithMultipleLoops")
    public void load() {
        List<Customer> customers = customersDataStorage.load();
        if (customers != null) {
            for (Customer customer : customers) {
                customersManager.addCustomer(customer);
            }
        }

        List<EmailTemplate> templates = emailTemplatesDataStorage.load();
        if (templates != null) {
            for (EmailTemplate template : templates) {
                emailTemplatesManager.addEmailTemplate(template);
            }
        }

        List<Phase> phases = phasesDataStorage.load();
        if (phases != null) {
            for (Phase phase : phases) {
                phasesManager.addPhase(phase);
            }
        }

        List<Pipeline> pipelines = pipelinesDataStorage.load();
        if (pipelines != null) {
            for (Pipeline pipeline : pipelines) {
                pipelinesManager.addPipeline(pipeline);
            }
        }
    }

    public void save() {
        phasesDataStorage.save(phasesManager.getPhases());
        pipelinesDataStorage.save(pipelinesManager.getPipelines());
        customersDataStorage.save(customersManager.getCustomers());
        emailTemplatesDataStorage.save(emailTemplatesManager.getEmailTemplates());
    }
}
