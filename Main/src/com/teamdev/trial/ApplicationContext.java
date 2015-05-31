package com.teamdev.trial;

import com.teamdev.trial.data.Customers;
import com.teamdev.trial.data.EmailTemplates;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Vladimir Ikryanov
 */
public class ApplicationContext {

    private Customers customers;
    private EmailTemplates emailTemplates;
    private ApplicationSettings settings;

    public ApplicationContext(ApplicationSettings settings) {
        this.settings = settings;
    }

    public Customers getCustomers() {
        return customers;
    }

    public EmailTemplates getEmailTemplates() {
        return emailTemplates;
    }

    public void load() throws FileNotFoundException {
        customers = Customers.load(new FileReader(settings.getCustomersFile()));
        emailTemplates = EmailTemplates.load(new FileReader(settings.getEmailTemplatesFile()));
    }

    public void save() throws IOException {
        FileWriter writer = new FileWriter(settings.getCustomersFile());
        Customers.save(customers, writer);
        writer.close();
    }
}
