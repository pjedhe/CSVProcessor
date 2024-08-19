package org.tutorial;

import com.google.gson.Gson;
import lombok.val;
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
    public static void main( String[] args ) throws IOException {
        ClassLoader classLoader = App.class.getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("testcustomer.csv")).getFile());
        List<Customer> customers = CustomerCSVService.getCustomers(file);
        Gson gson = new Gson();
        String url = "http://localhost:8080/api/create";
        for(Customer customer: customers) {
            System.out.println(gson.toJson(customer));
            post(url, gson.toJson(customer) );
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
