package com.teamdev.trial.data;

import com.google.gson.annotations.Expose;

/**
 * @author Vladimir Ikryanov
 */
public class PhaseState {

    public enum State {
        OPENED, CLOSED, CANCELED
    }

    @Expose
    private int phaseId;

    @Expose
    private int customOffsetInDays;

    @Expose
    private State state;

    private PhaseState() {
    }

    public PhaseState(int phaseId, State state) {
        this.phaseId = phaseId;
        this.state = state;
    }

    public int getPhaseId() {
        return phaseId;
    }

    public int getCustomOffsetInDays() {
        return customOffsetInDays;
    }

    public void setCustomOffsetInDays(int customOffsetInDays) {
        this.customOffsetInDays = customOffsetInDays;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
