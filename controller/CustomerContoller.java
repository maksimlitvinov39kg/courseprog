package com.example.courseprog.Controller;


import com.example.courseprog.Repositories.CustomerRepository;
import com.example.courseprog.Service.CustomerService;
import com.example.courseprog.Service.TransactionService;
import com.example.courseprog.Tables.Customer;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class CustomerContoller {

    private CustomerService customerService;
    private TransactionService transactionService;
    public CustomerContoller(CustomerService customerService,TransactionService transactionService){
        this.customerService = customerService;
        this.transactionService = transactionService;
    }

    @GetMapping("/customer")
    public String getCustomerPage(Model model){
        List<Customer> customers = customerService.getAll();
        model.addAttribute("customers",customers);
        return "customer_page";
    }
    @PostMapping(value = "/upload")
    public String  uploadMultipart(@RequestParam("file")MultipartFile file) throws IOException{
        customerService.customerLoad(file);
        return "redirect:/";
    }
    @PostMapping(value = "/uploadTransactions")
    public String  uploadMultipart1(@RequestParam("transfile")MultipartFile file) throws IOException{
        transactionService.TransactionLoad(file);
        return "redirect:/";
    }

    @PostMapping("downloadCustommer")
    public void downloadCustommer(HttpServletResponse session){
        try {
            customerService.customerWrite(session);
        }catch (IOException e){
        }
    }
    @PostMapping("downloadTransactions")
    public void downloadTransactions(HttpServletResponse session){
        try {
            transactionService.TransactionsWrite(session);
        }catch (IOException e){
        }
    }

    @GetMapping("/")
    public String mainPage(){
        return "main";
    }



}
