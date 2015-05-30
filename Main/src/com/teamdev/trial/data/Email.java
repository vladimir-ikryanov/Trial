package com.teamdev.trial.data;

/**
 * @author Vladimir Ikryanov
 */
public class Email {

    private final String email;

    public Email(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return email;
    }
}
