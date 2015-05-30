package com.teamdev.trial.data;

import java.util.List;

/**
 * @author Vladimir Ikryanov
 */
public class Pipeline {

    private final String name;
    private final List<Phase> phases;

    public Pipeline(String name, List<Phase> phases) {
        this.name = name;
        this.phases = phases;
    }

    public String getName() {
        return name;
    }

    public List<Phase> getPhases() {
        return phases;
    }
}
