package com.teamdev.trial;

import com.teamdev.trial.data.Phase;
import com.teamdev.trial.data.PhaseState;

import javax.swing.*;
import java.awt.*;

/**
 * @author Vladimir Ikryanov
 */
public class PhasePane extends JPanel {

    public PhasePane(ApplicationContext context, PhaseState phaseState) {
        Phase phase = context.getPhasesManager().getPhaseById(phaseState.getPhaseId());
        Color openedColor = new Color(40, 40, 40);
        Color closedColor = new Color(0, 128, 64);
        Color canceledColor = new Color(213, 106, 0);
        setBackground(openedColor);

        JLabel label = new JLabel(phase.getName());
        label.setForeground(Color.LIGHT_GRAY);
        if (phaseState.getState() == PhaseState.State.CLOSED) {
            setBackground(closedColor);
            label.setForeground(Color.WHITE);
            setToolTipText("Completed");
        }
        if (phaseState.getState() == PhaseState.State.CANCELED) {
            setBackground(canceledColor);
            label.setForeground(Color.WHITE);
            setToolTipText("Canceled");
        }
        label.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        setLayout(new BorderLayout());
        add(label, BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
    }
}
