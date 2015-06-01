package com.teamdev.trial.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Vladimir Ikryanov
 */
public class PipelineFactory {

    public static Pipeline create30DaysEvaluation(Date startDate) {
        List<Phase> phases = new ArrayList<Phase>();
        phases.add(new Phase("Day", 1, 0, Phase.State.OPENED));
        phases.add(new Phase("Week", 7, 1, Phase.State.OPENED));
        phases.add(new Phase("Offer", 20, 2, Phase.State.OPENED));
        phases.add(new Phase("Pre-Finish", 29, 3, Phase.State.OPENED));
        phases.add(new Phase("Feedback", 30, 4, Phase.State.OPENED));
        phases.add(new Phase("Finish", 30, -1, Phase.State.OPENED));
        return new Pipeline("30-Days Evaluation", phases, startDate);
    }
}
