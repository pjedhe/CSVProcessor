package org.tutorial.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.tutorial.dto.Customer;

public class CSVParserRoute extends RouteBuilder {

    private final BindyCsvDataFormat bindy=new BindyCsvDataFormat(Customer.class);;

    @Override
    public void configure() throws Exception {

    }
}
