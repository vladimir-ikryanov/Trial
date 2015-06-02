package com.teamdev.trial;

import com.teamdev.trial.data.PhaseState;

import javax.swing.*;
import java.awt.*;

/**
 * @author Vladimir Ikryanov
 */
public class PhaseGluePane extends JPanel {

    private final PhaseState prevPhaseState;
    private final PhaseState nextPhaseState;

    public PhaseGluePane(PhaseState prevPhaseState, PhaseState nextPhaseState) {
        this.prevPhaseState = prevPhaseState;
        this.nextPhaseState = nextPhaseState;
        setOpaque(false);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color openedColor = new Color(40, 40, 40);
        Color closedColor = new Color(0, 128, 64);
        Color canceledColor = new Color(213, 106, 0);

        if (nextPhaseState != null) {
            if (nextPhaseState.getState() == PhaseState.State.CLOSED) {
                graphics2D.setColor(closedColor);
            }
            if (nextPhaseState.getState() == PhaseState.State.CANCELED) {
                graphics2D.setColor(canceledColor);
            }
            if (nextPhaseState.getState() == PhaseState.State.OPENED) {
                graphics2D.setColor(openedColor);
            }
            graphics2D.fillRect(0, 0, getWidth(), getHeight());
        }

        if (prevPhaseState.getState() == PhaseState.State.CLOSED) {
            graphics2D.setColor(closedColor);
        }
        if (prevPhaseState.getState() == PhaseState.State.CANCELED) {
            graphics2D.setColor(canceledColor);
        }
        if (prevPhaseState.getState() == PhaseState.State.OPENED) {
            graphics2D.setColor(openedColor);
        }
        graphics2D.fillPolygon(new int[]{0, getWidth(), 0}, new int[]{0, getHeight() / 2, getHeight()}, 3);
    }
}
