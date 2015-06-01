package com.teamdev.trial.data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vladimir Ikryanov
 */
public class EmailTemplatesManager {

    private final List<EmailTemplate> emailTemplates;

    public EmailTemplatesManager() {
        this.emailTemplates = new ArrayList<EmailTemplate>();
    }

    public void addEmailTemplate(EmailTemplate emailTemplate) {
        if (!emailTemplates.contains(emailTemplate)) {
            emailTemplates.add(emailTemplate);
        }
    }

    public List<EmailTemplate> getEmailTemplates() {
        return new ArrayList<EmailTemplate>(emailTemplates);
    }

    public EmailTemplate getEmailTemplateById(int id) {
        for (EmailTemplate template : emailTemplates) {
            if (template.getId() == id) {
                return template;
            }
        }
        return null;
    }
}
