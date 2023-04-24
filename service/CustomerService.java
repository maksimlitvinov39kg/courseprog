package com.example.courseprog.Service;
import com.example.courseprog.Repositories.CustomerRepository;
import com.example.courseprog.Tables.Customer;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAll(){
        return customerRepository.findAll();
    }
    public Customer getById(Long ID){
       return customerRepository.findByCustomerId(ID);
    }

    public void customerLoad(MultipartFile file) throws IOException {
        List<Customer> users = new ArrayList<>();
        InputStream stream = file.getInputStream();

        CsvParserSettings settings = new CsvParserSettings();
        settings.setHeaderExtractionEnabled(true);
        settings.setDelimiterDetectionEnabled(true);

        CsvParser parser = new CsvParser(settings);
        List<Record> parseAllRecords = parser.parseAllRecords(stream);
        parseAllRecords.forEach(record -> {
            Customer customer = new Customer();
            customer.setCustomerId(Long.parseLong(record.getString("customer_id")));
            customer.setGender(Integer.parseInt(record.getString(("gender"))));
            users.add(customer);
        });
        customerRepository.saveAll(users);
    }
    public boolean customerWrite(HttpServletResponse response) throws IOException{
            String filename = "customer.csv";
            List<Customer> customers = customerRepository.findAll();
            response.setContentType("text/csv");
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=\""+filename+"\"");
            PrintWriter writer = response.getWriter();

            String header ="customer_id,gender";
            writer.println(header);
            for (Customer customer:customers){
                writer.println(customer.toString());
            }
            writer.close();
            return true;
    }
}
