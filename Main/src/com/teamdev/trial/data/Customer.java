package com.teamdev.trial.data;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vladimir Ikryanov
 */
public class Customer {

    public enum State {
        WIN, LOSS, UNKNOWN
    }

    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private String email;
    @Expose
    private Pipeline pipeline;
    @Expose
    private State state;

    private final List<CustomerListener> listeners;

    public Customer() {
        listeners = new ArrayList<CustomerListener>();
    }

    public void addCustomersListener(CustomerListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    public void removeCustomerListener(CustomerListener listener) {
        listeners.remove(listener);
    }

    public List<CustomerListener> getCustomerListeners() {
        return new ArrayList<CustomerListener>(listeners);
    }

    public void fireOnCustomerChanged() {
        CustomerEvent event = new CustomerEvent(this);
        for (CustomerListener listener : getCustomerListeners()) {
            listener.onCustomerChanged(event);
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        fireOnCustomerChanged();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        fireOnCustomerChanged();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        fireOnCustomerChanged();
    }

    public Pipeline getPipeline() {
        return pipeline;
    }

    public void setPipeline(Pipeline pipeline) {
        this.pipeline = pipeline;
        fireOnCustomerChanged();
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
        fireOnCustomerChanged();
    }
}
