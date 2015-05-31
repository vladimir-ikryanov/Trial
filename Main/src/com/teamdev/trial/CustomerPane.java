package com.teamdev.trial;

import com.teamdev.trial.data.Customer;
import com.teamdev.trial.data.Phase;
import com.teamdev.trial.data.Pipeline;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static java.awt.GridBagConstraints.*;

/**
 * @author Vladimir Ikryanov
 */
public class CustomerPane extends JPanel {
    public CustomerPane(Customer customer) {
        setLayout(new GridBagLayout());
        add(createCustomerPane(customer), new GridBagConstraints(
                0, 0, 1, 1, 0.0, 0.0, WEST, NONE, new Insets(0, 0, 0, 0), 0, 0));
        add(createPipelinePane(customer), new GridBagConstraints(
                1, 0, 1, 1, 1.0, 0.0, WEST, HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
    }

    private Component createCustomerPane(Customer customer) {
        JPanel result = new JPanel(new BorderLayout());
        result.setOpaque(false);
        result.add(new JLabel(customer.getFirstName() + " " + customer.getLastName()), BorderLayout.CENTER);
        result.add(new JLabel(customer.getEmail()), BorderLayout.SOUTH);
        return result;
    }

    private Component createPipelinePane(Customer customer) {
        JPanel result = new JPanel(new FlowLayout(FlowLayout.LEFT));
        result.setOpaque(false);
        Pipeline pipeline = customer.getPipeline();
        List<Phase> phases = pipeline.getPhases();
        for (Phase phase : phases) {
            result.add(new JLabel(phase.getName() + ": " + phase.getState()));
        }
        return result;
    }
}
