package com.teamdev.trial.ui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * @author Vladimir Ikryanov
 */
public class LightScrollPane extends JComponent {

    private static final int SCROLL_BAR_ALPHA_ROLLOVER = 150;
    private static final int THUMB_BORDER_SIZE = 2;
    private static final int THUMB_SIZE = 6;
    private static final Color THUMB_COLOR = Color.BLACK;

    private final JScrollPane scrollPane;
    private final JScrollBar verticalScrollBar;
    private final JScrollBar horizontalScrollBar;

    public LightScrollPane(JScrollPane pane, boolean autoHideScrollBars) {
        scrollPane = pane;
        scrollPane.setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setVisible(true);
        verticalScrollBar.setOpaque(false);
        verticalScrollBar.setUI(new MyScrollBarUI());

        horizontalScrollBar = scrollPane.getHorizontalScrollBar();
        horizontalScrollBar.setVisible(true);
        horizontalScrollBar.setOpaque(false);
        horizontalScrollBar.setUI(new MyScrollBarUI());

        final JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setLayer(verticalScrollBar, JLayeredPane.PALETTE_LAYER);
        layeredPane.setLayer(horizontalScrollBar, JLayeredPane.PALETTE_LAYER);

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setLayout(new ScrollPaneLayout() {
            @Override
            public void layoutContainer(Container parent) {
                int width = getWidth();
                int height = getHeight();
                int scrollBarSize = THUMB_SIZE + THUMB_BORDER_SIZE * 2;
                viewport.setBounds(0, 0, width, height);
                Rectangle viewRect = viewport.getViewRect();
                Dimension viewSize = viewport.getViewSize();
                boolean verticalScrollBarVisible = viewSize.getHeight() > viewRect.getHeight();
                boolean horizontalScrollBarVisible = viewSize.getWidth() > viewRect.getWidth();
                int cornerOffset = verticalScrollBarVisible && horizontalScrollBarVisible ? scrollBarSize : 0;
                if (verticalScrollBarVisible) {
                    verticalScrollBar.setBounds(width - scrollBarSize, 0,
                            scrollBarSize, height - cornerOffset);
                }
                if (horizontalScrollBarVisible) {
                    horizontalScrollBar.setBounds(0, height - scrollBarSize,
                            width - cornerOffset, scrollBarSize);
                }
            }
        });

        layeredPane.add(horizontalScrollBar, 0);
        layeredPane.add(verticalScrollBar, 1);
        layeredPane.add(scrollPane, 2);

        setLayout(new BorderLayout() {
            @Override
            public void layoutContainer(Container target) {
                super.layoutContainer(target);
                scrollPane.setBounds(0, 0, getWidth(), getHeight());
            }
        });
        add(layeredPane, BorderLayout.CENTER);
        layeredPane.setBackground(Color.BLUE);

        if (autoHideScrollBars) {
            AutoHideScrollBarsStrategy hideScrollBarsStrategy = new AutoHideScrollBarsStrategy(scrollPane);
            hideScrollBarsStrategy.addAutoHideScrollBarsStrategyListener(new AutoHideScrollBarsStrategyListener() {
                public void showScrollBars() {
                    if (layeredPane.getIndexOf(horizontalScrollBar) < 0) {
                        layeredPane.add(horizontalScrollBar, 0);
                    }
                    if (layeredPane.getIndexOf(verticalScrollBar) < 0) {
                        layeredPane.add(verticalScrollBar, 1);
                    }
                    layeredPane.validate();
                    layeredPane.repaint();
                }

                public void hideScrollBars() {
                    layeredPane.remove(verticalScrollBar);
                    layeredPane.remove(horizontalScrollBar);
                    layeredPane.validate();
                    layeredPane.repaint();
                }
            });
        }
    }

    public LightScrollPane(JComponent component) {
        this(component, true);
    }

    public LightScrollPane(JComponent component, boolean autoHideScrollBars) {
        this(new JScrollPane(component), autoHideScrollBars);
    }

    public JViewport getViewport() {
        return scrollPane.getViewport();
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    private static class MyScrollBarButton extends JButton {
        private MyScrollBarButton() {
            setOpaque(false);
            setFocusable(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setBorder(BorderFactory.createEmptyBorder());
        }
    }

    public static class MyScrollBarUI extends BasicScrollBarUI {

        @Override
        protected JButton createDecreaseButton(int orientation) {
            return new MyScrollBarButton();
        }

        @Override
        protected JButton createIncreaseButton(int orientation) {
            return new MyScrollBarButton();
        }

        @Override
        public Dimension getMaximumSize(JComponent c) {
            Dimension size = super.getMaximumSize(c);
            if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
                size.width = THUMB_SIZE + THUMB_BORDER_SIZE * 2;
            } else {
                size.height = THUMB_SIZE + THUMB_BORDER_SIZE * 2;
            }
            return size;
        }

        @Override
        public Dimension getPreferredSize(JComponent c) {
            Dimension preferredSize = super.getPreferredSize(c);
            if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
                preferredSize.width = THUMB_SIZE + THUMB_BORDER_SIZE * 2;
            } else {
                preferredSize.height = THUMB_SIZE + THUMB_BORDER_SIZE * 2;
            }
            return preferredSize;
        }

        @Override
        protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        }

        @Override
        protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
            int alpha = SCROLL_BAR_ALPHA_ROLLOVER;
            int orientation = scrollbar.getOrientation();
            int arc = THUMB_SIZE;
            int x = thumbBounds.x + THUMB_BORDER_SIZE;
            int y = thumbBounds.y + THUMB_BORDER_SIZE;

            int width = orientation == JScrollBar.VERTICAL ?
                    THUMB_SIZE : thumbBounds.width - (THUMB_BORDER_SIZE * 2);
            width = Math.max(width, THUMB_SIZE);

            int height = orientation == JScrollBar.VERTICAL ?
                    thumbBounds.height - (THUMB_BORDER_SIZE * 2) : THUMB_SIZE;
            height = Math.max(height, THUMB_SIZE);

            Graphics2D graphics2D = (Graphics2D) g.create();
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2D.setColor(new Color(THUMB_COLOR.getRed(),
                    THUMB_COLOR.getGreen(), THUMB_COLOR.getBlue(), alpha));
            graphics2D.fillRoundRect(x, y, width, height, arc, arc);
            graphics2D.dispose();
        }
    }

    private static interface AutoHideScrollBarsStrategyListener {

        public void showScrollBars();

        public void hideScrollBars();
    }

    private static class AutoHideScrollBarsStrategy {

        private final Timer hideTimer;
        private java.util.List<AutoHideScrollBarsStrategyListener> listeners;

        private AutoHideScrollBarsStrategy(final JScrollPane scrollPane) {
            listeners = new ArrayList<AutoHideScrollBarsStrategyListener>();
            hideTimer = new Timer(2000, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    for (AutoHideScrollBarsStrategyListener listener : getAutoHideScrollBarsStrategyListeners()) {
                        listener.hideScrollBars();
                    }
                }
            });
            hideTimer.setRepeats(false);

            scrollPane.getViewport().addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    for (AutoHideScrollBarsStrategyListener listener : getAutoHideScrollBarsStrategyListeners()) {
                        listener.showScrollBars();
                    }
                    hideTimer.restart();
                }
            });
        }

        public void addAutoHideScrollBarsStrategyListener(AutoHideScrollBarsStrategyListener listener) {
            listeners.add(listener);
        }

        public java.util.List<AutoHideScrollBarsStrategyListener> getAutoHideScrollBarsStrategyListeners() {
            return new ArrayList<AutoHideScrollBarsStrategyListener>(listeners);
        }
    }

    public static void main(String[] args) {
        JTextArea textArea = new JTextArea();
        LightScrollPane scrollPane = new LightScrollPane(textArea);

        JFrame frame = new JFrame();
        frame.getContentPane().add(new JTextField(), BorderLayout.NORTH);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.setSize(500, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
