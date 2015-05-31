package com.teamdev.trial.data;

import com.google.gson.annotations.Expose;

/**
 * @author Vladimir Ikryanov
 */
public class Phase {

    public enum State {
        OPENED, CLOSED, CANCELED
    }

    @Expose
    private String name;
    @Expose
    private int offsetInDays;
    @Expose
    private int emailId;
    @Expose
    private State state;

    private Phase() {
    }

    public Phase(String name, int offsetInDays, int emailId, State state) {
        this.name = name;
        this.offsetInDays = offsetInDays;
        this.emailId = emailId;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public int getOffsetInDays() {
        return offsetInDays;
    }

    public int getEmailId() {
        return emailId;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
