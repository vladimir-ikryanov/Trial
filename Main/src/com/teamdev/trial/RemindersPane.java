package com.teamdev.trial;

import com.teamdev.trial.data.CustomersManagerEvent;
import com.teamdev.trial.data.CustomersManagerListener;
import com.teamdev.trial.ui.LightScrollPane;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static java.awt.GridBagConstraints.*;

/**
 * @author Vladimir Ikryanov
 */
public class RemindersPane extends JPanel {

    private final JPanel remindersPane;
    private final ApplicationContext context;
    private final RemindersBuilder remindersBuilder;
    private final DefaultCustomersManagerListener customersManagerListener;

    public RemindersPane(ApplicationContext context) {
        this.context = context;
        this.remindersPane = new JPanel(new GridBagLayout());
        this.remindersPane.setOpaque(false);
        this.remindersBuilder = new RemindersBuilder(context);
        this.customersManagerListener = new DefaultCustomersManagerListener();

        LightScrollPane scrollPane = new LightScrollPane(remindersPane);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.getScrollPane().getVerticalScrollBar().setUnitIncrement(50);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        setOpaque(false);
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 20));

        initializeUI();
    }

    @Override
    public void addNotify() {
        super.addNotify();
        context.getCustomersManager().addCustomersManagerListener(customersManagerListener);
    }

    @Override
    public void removeNotify() {
        context.getCustomersManager().removeCustomersManagerListener(customersManagerListener);
        super.removeNotify();
    }

    private void initializeUI() {
        remindersPane.removeAll();
        List<Reminder> reminders = remindersBuilder.getRemindersForToday();
        if (reminders.isEmpty()) {
            JLabel label = new JLabel("No Tasks for Today");
            label.setForeground(Color.GRAY);
            label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            label.setVerticalAlignment(SwingConstants.TOP);
            label.setVerticalTextPosition(SwingConstants.TOP);
            remindersPane.add(label, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, NORTH, BOTH, new Insets(0, 0, 0, 0), 0, 0));
        } else {
            for (int i = 0; i < reminders.size(); i++) {
                Reminder reminder = reminders.get(i);
                if (reminder instanceof FinishReminder) {
                    remindersPane.add(new FinishReminderPane((FinishReminder) reminder), new GridBagConstraints(0, i, 1, 1, 0.0, 0.0, NORTH, HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
                }
                if (reminder instanceof EmailReminder) {
                    remindersPane.add(new EmailReminderPane(context, (EmailReminder) reminder), new GridBagConstraints(0, i, 1, 1, 0.0, 0.0, NORTH, HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
                }
            }
            remindersPane.add(Box.createVerticalGlue(), new GridBagConstraints(0, reminders.size(), 1, 1, 1.0, 1.0, NORTH, BOTH, new Insets(0, 0, 0, 0), 0, 0));
        }
        remindersPane.validate();
        remindersPane.repaint();
    }

    private class DefaultCustomersManagerListener implements CustomersManagerListener {
        @Override
        public void onCustomerAdded(CustomersManagerEvent event) {
            initializeUI();
        }

        @Override
        public void onCustomerRemoved(CustomersManagerEvent event) {
            initializeUI();
        }

        @Override
        public void onCustomerChanged(CustomersManagerEvent event) {
            initializeUI();
        }
    }
}
