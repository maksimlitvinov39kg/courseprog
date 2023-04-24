package com.example.courseprog.Service;
import com.example.courseprog.Repositories.TransactionsRepository;
import com.example.courseprog.Tables.Customer;
import com.example.courseprog.Tables.Transactions;
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
public class TransactionService {
    private final TransactionsRepository transactionsRepository;
    private DispersionService dispersionService;

    @Autowired
    public TransactionService(TransactionsRepository transactionsRepository,
                              DispersionService dispersionService) {
        this.transactionsRepository = transactionsRepository;
        this.dispersionService = dispersionService;
    }

    public List<Transactions> getAll(){
        return transactionsRepository.findAll();
    }

    public void TransactionLoad(MultipartFile file) throws IOException {
        List<Transactions> users = new ArrayList<>();
        InputStream stream = file.getInputStream();

        CsvParserSettings settings = new CsvParserSettings();
        settings.setHeaderExtractionEnabled(true);
        settings.setDelimiterDetectionEnabled(true);

        CsvParser parser = new CsvParser(settings);
        List<Record> parseAllRecords = parser.parseAllRecords(stream);
        parseAllRecords.forEach(record -> {
            Transactions tran = new Transactions();
            tran.setCustomerId(Long.parseLong(record.getString("customer_id")));
            tran.setTranDatetime(record.getString(("tr_datetime")));
            tran.setMccCode(Integer.parseInt(record.getString("mcc_code")));
            tran.setTranType(Integer.parseInt(record.getString("tr_type")));
            tran.setAmount(Long.parseLong(record.getString("amount")));
            tran.setTermId(Long.parseLong(record.getString("term_id")));
            users.add(tran);
        });
        transactionsRepository.saveAll(users);
        dispersionService.calculateAll();
    }
    public boolean TransactionsWrite(HttpServletResponse response) throws IOException{
        String filename = "transactions.csv";
        List<Transactions> transactions = transactionsRepository.findAll();
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=\""+filename+"\"");
        PrintWriter writer = response.getWriter();

        String header ="customer_id,tr_datetime,mcc_code,tr_type,amount,term_id";
        writer.println(header);
        for (Transactions transactions1 : transactions){
            writer.println(transactions.toString());
        }
        writer.close();
        return true;
    }

}
