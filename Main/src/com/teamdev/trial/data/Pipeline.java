package com.teamdev.trial.data;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Vladimir Ikryanov
 */
public class Pipeline {

    @Expose
    private String name;
    @Expose
    private Date startDate;
    @Expose
    private List<Phase> phases;

    private Pipeline() {
    }

    public Pipeline(String name, List<Phase> phases, Date startDate) {
        this.name = name;
        this.phases = phases;
        this.startDate = new Date(startDate.getTime());
    }

    public String getName() {
        return name;
    }

    public Date getStartDate() {
        return new Date(startDate.getTime());
    }

    public List<Phase> getPhases() {
        return new ArrayList<Phase>(phases);
    }
}
