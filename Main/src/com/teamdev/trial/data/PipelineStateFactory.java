package com.teamdev.trial.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Vladimir Ikryanov
 */
public class PipelineStateFactory {

    public static PipelineState create(Date startDate, Pipeline pipeline) {
        List<PhaseState> phaseStates = new ArrayList<PhaseState>();
        for (int phaseId : pipeline.getPhasesIds()) {
            phaseStates.add(new PhaseState(phaseId, PhaseState.State.OPENED));
        }
        return new PipelineState(pipeline.getId(), startDate, phaseStates);
    }
}
