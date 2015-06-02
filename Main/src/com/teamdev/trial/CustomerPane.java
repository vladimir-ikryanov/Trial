package com.teamdev.trial;

import com.teamdev.trial.data.Customer;
import com.teamdev.trial.data.Phase;
import com.teamdev.trial.data.PhaseState;
import com.teamdev.trial.data.PipelineState;
import com.teamdev.trial.ui.ButtonLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
        setOpaque(false);
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        JTextField firstLastNameTextField = new JTextField(customer.getFirstName() + ' ' + customer.getLastName());
        firstLastNameTextField.setOpaque(false);
        firstLastNameTextField.setEditable(false);
        firstLastNameTextField.setBorder(BorderFactory.createEmptyBorder());
        firstLastNameTextField.setPreferredSize(new Dimension(250, 25));
        firstLastNameTextField.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
        firstLastNameTextField.setForeground(Color.WHITE);

        final JTextField emailTextField = new JTextField(customer.getEmail());
        emailTextField.setOpaque(false);
        emailTextField.setEditable(false);
        emailTextField.setBorder(BorderFactory.createEmptyBorder());
        emailTextField.setForeground(Color.GRAY);

        final JPanel mutablePane = new JPanel(new BorderLayout());
        mutablePane.setOpaque(false);
        mutablePane.add(emailTextField, BorderLayout.WEST);

        final Component winLossPane = createWinLossPane();

//        addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseEntered(MouseEvent e) {
//                mutablePane.removeAll();
//                mutablePane.add(winLossPane, BorderLayout.WEST);
//                validate();
//                repaint();
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//                mutablePane.removeAll();
//                mutablePane.add(emailTextField, BorderLayout.WEST);
//                validate();
//                repaint();
//            }
//        });

        add(firstLastNameTextField, new GridBagConstraints(
                0, 0, 1, 1, 0.0, 0.0, WEST, HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        add(emailTextField, new GridBagConstraints(
                0, 1, 1, 1, 0.0, 0.0, WEST, HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        add(createPipelinePane(), new GridBagConstraints(
                1, 0, 1, 2, 1.0, 1.0, WEST, BOTH, new Insets(0, 0, 0, 0), 0, 0));
    }

    private Component createWinLossPane() {
        JPanel result = new JPanel();
        result.setOpaque(false);
        result.add(createWinButton());
        result.add(createLossButton());
        result.add(createRemoveButton());
        return result;
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
        JPanel result = new JPanel(new FlowLayout(FlowLayout.LEFT));
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
