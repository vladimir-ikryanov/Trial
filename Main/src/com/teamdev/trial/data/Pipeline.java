package com.teamdev.trial.data;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vladimir Ikryanov
 */
public class Pipeline {

    @Expose
    private int id;

    @Expose
    private String name;

    @Expose
    private List<Integer> phasesIds;

    private Pipeline() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getPhasesIds() {
        return new ArrayList<Integer>(phasesIds);
    }

    @Override
    public String toString() {
        return name;
    }
}
