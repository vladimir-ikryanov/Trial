package com.teamdev.trial.data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vladimir Ikryanov
 */
public class PhasesManager {

    private final List<Phase> phases;

    public PhasesManager() {
        this.phases = new ArrayList<Phase>();
    }

    public void addPhase(Phase phase) {
        if (!phases.contains(phase)) {
            phases.add(phase);
        }
    }

    public List<Phase> getPhases() {
        return new ArrayList<Phase>(phases);
    }

    public Phase getPhaseById(int id) {
        for (Phase phase : phases) {
            if (phase.getId() == id) {
                return phase;
            }
        }
        return null;
    }
}
