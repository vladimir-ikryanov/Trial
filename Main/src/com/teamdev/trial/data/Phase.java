package com.teamdev.trial.data;

/**
 * @author Vladimir Ikryanov
 */
public class Phase {

    public enum State {
        OPENED, CLOSED, CANCELED
    }

    private String name;
    private int offsetInDays;
    private int emailId;
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
