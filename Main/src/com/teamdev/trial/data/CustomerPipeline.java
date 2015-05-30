package com.teamdev.trial.data;

import java.util.Date;

/**
 * @author Vladimir Ikryanov
 */
public class CustomerPipeline {

    private final Customer customer;
    private final Pipeline pipeline;
    private final Date startDate;

    public CustomerPipeline(Customer customer, Pipeline pipeline, Date startDate) {
        this.customer = customer;
        this.pipeline = pipeline;
        this.startDate = startDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Pipeline getPipeline() {
        return pipeline;
    }

    public Date getStartDate() {
        return startDate;
    }
}
