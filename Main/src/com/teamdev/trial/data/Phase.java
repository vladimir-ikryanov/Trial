package com.teamdev.trial.data;

import com.google.gson.annotations.Expose;

/**
 * @author Vladimir Ikryanov
 */
public class Phase {

    @Expose
    private int id;

    @Expose
    private String name;

    @Expose
    private int offsetInDays;

    @Expose
    private int emailTemplateId;

    private Phase() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getOffsetInDays() {
        return offsetInDays;
    }

    public int getEmailTemplateId() {
        return emailTemplateId;
    }
}
