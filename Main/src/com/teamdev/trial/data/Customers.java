package com.teamdev.trial.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vladimir Ikryanov
 */
public class Customers {

    public static Customers load(Reader reader) {
        Gson gson = new GsonBuilder().create();
        Customers result = gson.fromJson(reader, Customers.class);
        if (result == null) {
            result = new Customers(new ArrayList<Customer>());
        }
        return result;
    }

    public static void save(Customers customers, Writer writer) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(customers, writer);
    }

    private List<Customer> customers;

    private Customers() {
    }

    public Customers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Customer> getCustomers() {
        return new ArrayList<Customer>(customers);
    }

    public void addCustomer(Customer customer) {
        if (!customers.contains(customer)) {
            customers.add(customer);
        }
    }

    public void removeCustomer(Customer customer) {
        customers.remove(customer);
    }
}
