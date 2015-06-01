package com.teamdev.trial.data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vladimir Ikryanov
 */
public class EmailTemplates {

    private final List<EmailTemplate> templates;

    public EmailTemplates() {
        this.templates = new ArrayList<EmailTemplate>();
    }

    public void addEmailTemplate(EmailTemplate emailTemplate) {
        if (!templates.contains(emailTemplate)) {
            templates.add(emailTemplate);
        }
    }

    public EmailTemplate getEmailTemplateById(int id) {
        for (EmailTemplate template : templates) {
            if (template.getId() == id) {
                return template;
            }
        }
        return null;
    }
}
