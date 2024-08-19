package org.tutorial.service;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;


import org.tutorial.dto.Customer;

import java.io.File;

import java.io.IOException;
import java.util.List;

public class CustomerCSVService {

    public static List<Customer> getCustomers(String path) throws IOException {
        File csvFile = new File(path);
        return getCustomers(csvFile);
    }

    public static List<Customer> getCustomers(File csvFile) throws IOException {
        MappingIterator<Customer> personIter = new CsvMapper().readerWithTypedSchemaFor(Customer.class).readValues(csvFile);
        return personIter.readAll();
    }
}
