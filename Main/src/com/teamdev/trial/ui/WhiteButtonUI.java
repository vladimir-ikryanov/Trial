package com.teamdev.trial.ui;

import javax.swing.*;
import javax.swing.plaf.metal.MetalButtonUI;
import java.awt.*;

/**
 * @author Vladimir Ikryanov
 */
public class WhiteButtonUI extends MetalButtonUI {

    public WhiteButtonUI() {
    }

    @Override
    public void installDefaults(AbstractButton b) {
        super.installDefaults(b);
        b.setMargin(new Insets(5, 20, 5, 20));
        b.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.setColor(Color.WHITE);
        g2D.fillRect(0, 0, c.getWidth(), c.getHeight());
        super.paint(g, c);
        g2D.setColor(Color.LIGHT_GRAY);
        g2D.drawRect(0, 0, c.getWidth() - 1, c.getHeight() - 1);
    }

    @Override
    public void paintButtonPressed(Graphics g, AbstractButton b) {
        paintText(g, b, b.getBounds(), b.getText());
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, b.getSize().width, b.getSize().height);
    }

    @Override
    protected void paintFocus(Graphics g, AbstractButton b, Rectangle viewRect, Rectangle textRect, Rectangle iconRect) {
    }
}
