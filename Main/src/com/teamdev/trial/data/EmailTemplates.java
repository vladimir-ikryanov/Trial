package com.teamdev.trial.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vladimir Ikryanov
 */
public class EmailTemplates {

    public static EmailTemplates load(Reader reader) {
        Gson gson = new GsonBuilder().create();
        EmailTemplates result = gson.fromJson(reader, EmailTemplates.class);
        if (result == null) {
            result = new EmailTemplates(new ArrayList<EmailTemplate>());
        }
        return result;
    }

    private List<EmailTemplate> templates;

    private EmailTemplates() {
    }

    public EmailTemplates(List<EmailTemplate> templates) {
        this.templates = templates;
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
