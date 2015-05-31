package com.teamdev.trial.data;

/**
 * @author Vladimir Ikryanov
 */
public class Customer {

    public enum State {
        WIN, LOSS, UNKNOWN
    }

    private String firstName;
    private String lastName;
    private String email;
    private Pipeline pipeline;
    private State state;

    private Customer() {
    }

    public Customer(String firstName, String lastName, String email, Pipeline pipeline, State state) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.pipeline = pipeline;
        this.state = state;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Pipeline getPipeline() {
        return pipeline;
    }

    public void setPipeline(Pipeline pipeline) {
        this.pipeline = pipeline;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
