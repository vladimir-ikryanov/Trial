package com.teamdev.trial;

import com.teamdev.trial.data.Customer;
import com.teamdev.trial.ui.ButtonLabel;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Vladimir Ikryanov
 */
public class ApplicationFrame extends JFrame {

    private final ApplicationContext context;

    public ApplicationFrame(final ApplicationContext context) throws HeadlessException {
        this.context = context;
        setContentPane(createSplitPane());
        setBackground(new Color(55, 55, 55));
        addWindowListener(new MyWindowAdapter(context));
    }

    private JSplitPane createSplitPane() {
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setOpaque(false);
        splitPane.setResizeWeight(1.0);
        splitPane.setContinuousLayout(true);
        splitPane.setBorder(BorderFactory.createEmptyBorder());
        splitPane.setLeftComponent(createLeftPane());
        splitPane.setRightComponent(createRightPane());
        splitPane.setUI(new BasicSplitPaneUI() {
            @Override
            public BasicSplitPaneDivider createDefaultDivider() {
                return new BasicSplitPaneDivider(this) {
                    @Override
                    public void paint(Graphics g) {
                    }
                };
            }
        });
        return splitPane;
    }

    private Component createRightPane() {
        JPanel result = new JPanel(new BorderLayout());
        result.setOpaque(false);
        result.setMinimumSize(new Dimension(350, 200));
        result.add(createRightCaption(), BorderLayout.NORTH);
        result.add(createRemindersPane(), BorderLayout.CENTER);
        return result;
    }

    private Component createRightCaption() {
        JLabel result = new JLabel("Reminders");
        result.setOpaque(false);
        result.setFont(new Font("Segoe UI Light", Font.PLAIN, 36));
        result.setForeground(Color.GRAY);
        result.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 0));
        return result;
    }

    private Component createRemindersPane() {
        return new RemindersPane(context);
    }

    private Component createLeftPane() {
        JPanel result = new JPanel(new BorderLayout());
        result.setOpaque(false);
        result.add(createLeftCaption(), BorderLayout.NORTH);
        result.add(createLeftContent(), BorderLayout.CENTER);
        return result;
    }

    private Component createLeftContent() {
        JPanel result = new JPanel(new BorderLayout());
        result.setOpaque(false);
        result.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 0));
        result.add(createCustomersPane(), BorderLayout.CENTER);
        return result;
    }

    private Component createCustomersPane() {
        return new CustomersPane(context);
    }

    private Component createLeftCaption() {
        JLabel label = new JLabel("Customers");
        label.setForeground(Color.GRAY);
        label.setFont(new Font("Segoe UI Light", Font.PLAIN, 36));

        final ButtonLabel buttonLabel = new ButtonLabel("New Customer", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomerDialog dialog = new CustomerDialog(ApplicationFrame.this, context);
                dialog.setResizable(false);
                dialog.setLocationRelativeTo(ApplicationFrame.this);
                dialog.setVisible(true);

                Customer customer = dialog.getCustomer();
                if (customer != null) {
                    context.getCustomersManager().addCustomer(customer);
                }
            }
        });
        buttonLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        buttonLabel.setBorder(BorderFactory.createEmptyBorder(19, 10, 0, 0));

        JPanel result = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        result.setOpaque(false);
        result.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 0));
        result.add(label);
        result.add(buttonLabel);
        return result;
    }

    private static class MyWindowAdapter extends WindowAdapter {

        private final ApplicationContext context;

        private MyWindowAdapter(ApplicationContext context) {
            this.context = context;
        }

        @Override
        public void windowClosing(WindowEvent e) {
            context.save();
        }

        @Override
        public void windowDeactivated(WindowEvent e) {
            context.save();
        }
    }
}
