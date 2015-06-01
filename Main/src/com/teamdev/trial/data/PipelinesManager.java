package com.teamdev.trial.data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vladimir Ikryanov
 */
public class PipelinesManager {

    private final List<Pipeline> pipelines;

    public PipelinesManager() {
        this.pipelines = new ArrayList<Pipeline>();
    }

    public void addPipeline(Pipeline pipeline) {
        if (!pipelines.contains(pipeline)) {
            pipelines.add(pipeline);
        }
    }

    public List<Pipeline> getPipelines() {
        return new ArrayList<Pipeline>(pipelines);
    }

    public Pipeline getPipelineById(int id) {
        for (Pipeline pipeline : pipelines) {
            if (pipeline.getId() == id) {
                return pipeline;
            }
        }
        return null;
    }
}
