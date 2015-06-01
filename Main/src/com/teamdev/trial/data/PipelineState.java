package com.teamdev.trial.data;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Vladimir Ikryanov
 */
public class PipelineState {

    @Expose
    private int pipelineId;

    @Expose
    private Date startDate;

    @Expose
    private List<PhaseState> phaseStates;

    private PipelineState() {
    }

    public PipelineState(int pipelineId, Date startDate, List<PhaseState> phaseStates) {
        this.pipelineId = pipelineId;
        this.startDate = startDate;
        this.phaseStates = phaseStates;
    }

    public int getPipelineId() {
        return pipelineId;
    }

    public Date getStartDate() {
        return new Date(startDate.getTime());
    }

    public List<PhaseState> getPhaseStates() {
        return new ArrayList<PhaseState>(phaseStates);
    }
}
