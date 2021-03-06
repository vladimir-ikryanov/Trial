package com.teamdev.trial.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Vladimir Ikryanov
 */
public class ButtonLabel extends JLabel {

    public ButtonLabel(String text, final ActionListener actionListener) {
        this(text, Color.GRAY, actionListener);
    }

    public ButtonLabel(String text, final Color foregroundColor, final ActionListener actionListener) {
        super(text);
        setForeground(Color.GRAY);
        setFont(new Font("Segoe UI", Font.PLAIN, 14));
        setForeground(foregroundColor);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setForeground(foregroundColor);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                actionListener.actionPerformed(new ActionEvent(ButtonLabel.this, ActionEvent.ACTION_PERFORMED, ""));
            }
        });
    }
}
