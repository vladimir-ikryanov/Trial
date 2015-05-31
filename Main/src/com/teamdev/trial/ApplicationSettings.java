package com.teamdev.trial;

import java.io.File;

/**
 * @author Vladimir Ikryanov
 */
public class ApplicationSettings {

    public File getEmailTemplatesFile() {
        return new File("resources/email-templates.json");
    }

    public File getCustomersFile() {
        return new File("resources/customers.json");
    }
}
