package com.teamdev.trial.data;

/**
 * @author Vladimir Ikryanov
 */
public class Phase {

    private final String name;
    private final int offsetInDays;
    private final Action action;

    public Phase(String name, int offsetInDays, Action action) {
        this.name = name;
        this.offsetInDays = offsetInDays;
        this.action = action;
    }

    public String getName() {
        return name;
    }

    public int getOffsetInDays() {
        return offsetInDays;
    }

    public Action getAction() {
        return action;
    }
}
