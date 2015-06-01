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

    public EmailTemplate() {
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

    public void setId(int id) {
        this.id = id;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
