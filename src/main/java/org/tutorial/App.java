package org.tutorial;

import com.google.gson.Gson;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.tutorial.dto.Customer;
import org.tutorial.service.CustomerCSVService;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class App 
{
    public static final String TESTCUSTOMER_CSV = "testcustomer.csv";
    public static String CUSTOMER_SERVICE_HOST_PORT = "http://localhost:8080";
    public static String CUSTOMER_CREATE_PATH = "/api/create";
    public static String getCustomerServiceUrl(String[] args) {
        if(args!=null && args.length > 0) {
            return args[0] + CUSTOMER_CREATE_PATH ;
        }
        return CUSTOMER_SERVICE_HOST_PORT + CUSTOMER_CREATE_PATH;
    }

    public static File getCSVFile(String[] args) {
        if(args!=null && args.length > 1) {
            return new File(args[1]);
        }
        ClassLoader classLoader = App.class.getClassLoader();
        return new File(Objects.requireNonNull(classLoader.getResource(TESTCUSTOMER_CSV)).getFile());

    }
    public static void main( String[] args ) throws IOException {
        File file = getCSVFile(args);
        List<Customer> customers = CustomerCSVService.getCustomers(file);
        Gson gson = new Gson();

        String url = getCustomerServiceUrl(args);
        for(Customer customer: customers) {
            System.out.println(gson.toJson(customer));
            System.out.println(post(url, gson.toJson(customer) ));
        }

    }

    public static String post(String url, String jsonBody) {
        String result = null;
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new StringEntity(jsonBody, ContentType.APPLICATION_JSON));

        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            try (CloseableHttpResponse response = httpclient.execute(httpPost)) {

                result = EntityUtils.toString(response.getEntity());
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
}
