package com.teamdev.trial.data;

/**
 * @author Vladimir Ikryanov
 */
public class EmailTemplate {

    private int id;
    private String subject;
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
