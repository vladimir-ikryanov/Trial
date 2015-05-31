package com.teamdev.trial.data;

/**
 * @author Vladimir Ikryanov
 */
public interface CustomersManagerListener {

    void onCustomerAdded(CustomersManagerEvent event);

    void onCustomerRemoved(CustomersManagerEvent event);

    void onCustomerChanged(CustomersManagerEvent event);
}
