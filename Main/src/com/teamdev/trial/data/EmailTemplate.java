package com.teamdev.trial.data;

import com.google.gson.annotations.Expose;

/**
 * @author Vladimir Ikryanov
 */
public class EmailTemplate {

    @Expose
    private int id;
    @Expose
    private String subject;
    @Expose
    private String body;

    private EmailTemplate() {
    }

    public int getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }
}
