package com.teamdev.trial;

import com.teamdev.trial.data.Customer;
import com.teamdev.trial.data.Phase;
import com.teamdev.trial.data.PhaseState;
import com.teamdev.trial.data.PipelineState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static java.awt.GridBagConstraints.*;

/**
 * @author Vladimir Ikryanov
 */
public class CustomerPane extends JPanel {

    private final Customer customer;
    private final ApplicationContext context;

    public CustomerPane(ApplicationContext context, Customer customer) {
        this.context = context;
        this.customer = customer;
        setLayout(new GridBagLayout());
        add(createCustomerPane(), new GridBagConstraints(
                0, 0, 1, 1, 0.0, 0.0, WEST, NONE, new Insets(0, 0, 0, 0), 0, 0));
        add(createPipelinePane(), new GridBagConstraints(
                1, 0, 1, 1, 1.0, 0.0, WEST, HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        add(createWinLossPane(), new GridBagConstraints(
                2, 0, 1, 1, 0.0, 0.0, SOUTH, NONE, new Insets(0, 0, 0, 0), 0, 0));
    }

    private Component createWinLossPane() {
        JPanel result = new JPanel();
        result.add(createWinButton());
        result.add(createLossButton());
        result.add(createRemoveButton());
        return result;
    }

    private JButton createRemoveButton() {
        JButton button = new JButton("Remove");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                context.getCustomersManager().removeCustomer(customer);
            }
        });
        return button;
    }

    private JButton createLossButton() {
        JButton button = new JButton("Loss");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customer.setState(Customer.State.LOSS);
            }
        });
        return button;
    }

    private JButton createWinButton() {
        JButton button = new JButton("Win");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customer.setState(Customer.State.WIN);
            }
        });
        return button;
    }

    private Component createCustomerPane() {
        JPanel result = new JPanel(new BorderLayout());
        result.setOpaque(false);
        result.setPreferredSize(new Dimension(150, 50));
        result.add(new JLabel(customer.getFirstName() + " " + customer.getLastName()), BorderLayout.CENTER);
        result.add(new JLabel(customer.getEmail()), BorderLayout.SOUTH);
        return result;
    }

    private Component createPipelinePane() {
        JPanel result = new JPanel(new FlowLayout(FlowLayout.LEFT));
        result.setOpaque(false);
        PipelineState pipelineState = customer.getPipelineState();
        List<PhaseState> phaseStates = pipelineState.getPhaseStates();
        for (PhaseState phaseState : phaseStates) {
            Phase phase = context.getPhasesManager().getPhaseById(phaseState.getPhaseId());
            JLabel label = new JLabel(phase.getName());
            if (phaseState.getState() == PhaseState.State.CLOSED) {
                label.setForeground(Color.GREEN);
            }
            if (phaseState.getState() == PhaseState.State.CANCELED) {
                label.setForeground(Color.ORANGE);
            }
            result.add(label);
        }
        return result;
    }
}
