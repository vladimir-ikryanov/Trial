package com.teamdev.trial;

import com.google.gson.reflect.TypeToken;
import com.teamdev.trial.data.Customer;
import com.teamdev.trial.data.CustomersManager;
import com.teamdev.trial.data.JSONDataStorage;

import java.util.List;

/**
 * @author Vladimir Ikryanov
 */
public class ApplicationContext {

    private final CustomersManager customersManager;
    private final JSONDataStorage<List<Customer>> customersDataStorage;

    public ApplicationContext(ApplicationSettings settings) {
        this.customersManager = new CustomersManager();
        this.customersDataStorage = new JSONDataStorage<List<Customer>>(settings.getCustomersFile(),
                new TypeToken<List<Customer>>() {});
    }

    public CustomersManager getCustomersManager() {
        return customersManager;
    }

    public void load() throws Exception {
        List<Customer> customers = customersDataStorage.load();
        if (customers != null) {
            for (Customer customer : customers) {
                customersManager.addCustomer(customer);
            }
        }
    }

    public void save() throws Exception {
        customersDataStorage.save(customersManager.getCustomers());
    }
}
