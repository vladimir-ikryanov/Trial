package com.teamdev.trial;

import com.teamdev.trial.data.Customer;
import com.teamdev.trial.data.PhaseState;
import com.teamdev.trial.data.PipelineState;
import com.teamdev.trial.ui.ButtonLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static java.awt.GridBagConstraints.*;

/**
 * @author Vladimir Ikryanov
 */
public class CustomerPipelinePane extends JPanel {

    private final Customer customer;
    private final ApplicationContext context;

    public CustomerPipelinePane(ApplicationContext context, Customer customer) {
        this.context = context;
        this.customer = customer;

        setOpaque(false);
        setLayout(new GridBagLayout());
        add(createPipelinePane(), new GridBagConstraints(
                1, 0, 1, 2, 1.0, 1.0, WEST, BOTH, new Insets(0, 0, 0, 0), 0, 0));
    }

    private Component createWinLossPane() {
        JPanel result = new JPanel();
        result.setOpaque(false);
        result.add(createWinButton());
        result.add(createLossButton());
        result.add(createEditButton());
        result.add(createRemoveButton());
        return result;
    }

    private Component createEditButton() {
        ButtonLabel button = new ButtonLabel("Edit", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        return button;
    }

    private Component createRemoveButton() {
        ButtonLabel button = new ButtonLabel("Remove", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                context.getCustomersManager().removeCustomer(customer);
            }
        });
        return button;
    }

    private Component createLossButton() {
        ButtonLabel result = new ButtonLabel("Loss", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customer.setState(Customer.State.LOSS);
            }
        });
        return result;
    }

    private Component createWinButton() {
        ButtonLabel result = new ButtonLabel("Win", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customer.setState(Customer.State.WIN);
            }
        });
        return result;
    }

    private Component createPipelinePane() {
        JPanel result = new JPanel(new GridBagLayout());
        result.setOpaque(false);
        PipelineState pipelineState = customer.getPipelineState();
        List<PhaseState> phaseStates = pipelineState.getPhaseStates();
        for (int i = 0, j = 0; i < phaseStates.size(); i++, j += 2) {
            PhaseState prevPhaseState = phaseStates.get(i);
            PhaseState nextPhaseState = i + 1 < phaseStates.size() ? phaseStates.get(i + 1) : null;

            PhasePane phasePane = new PhasePane(context, prevPhaseState);
            PhaseGluePane phaseGluePane = new PhaseGluePane(prevPhaseState, nextPhaseState);

            result.add(phasePane, new GridBagConstraints(j, 0, 1, 1, 0.0, 1.0, WEST, BOTH, new Insets(0, 0, 0, 0), 0, 0));
            result.add(phaseGluePane, new GridBagConstraints(j + 1, 0, 1, 1, 0.0, 1.0, WEST, BOTH, new Insets(0, 0, 0, 0), 0, 0));
        }
        result.add(createWinLossPane(), new GridBagConstraints(
                phaseStates.size() * 2, 0, 1, 1, 0.0, 0.0, WEST, HORIZONTAL, new Insets(0, 10, 0, 0), 0, 0));
        result.add(Box.createHorizontalBox(), new GridBagConstraints(
                phaseStates.size() * 2 + 1, 0, 1, 1, 1.0, 1.0, WEST, BOTH, new Insets(0, 0, 0, 0), 0, 0));
        return result;
    }
}
