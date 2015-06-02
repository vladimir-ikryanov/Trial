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
        if (phaseState.getState() == PhaseState.State.CLOSED) {
            setBackground(closedColor);
        }
        if (phaseState.getState() == PhaseState.State.CANCELED) {
            setBackground(canceledColor);
        }
        JLabel label = new JLabel(phase.getName());
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        setLayout(new BorderLayout());
        add(label, BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
    }
}
