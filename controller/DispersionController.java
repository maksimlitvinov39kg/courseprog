package com.example.courseprog.Controller;

import com.example.courseprog.Service.DispersionService;
import com.example.courseprog.Tables.Dispersion;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Controller
public class DispersionController {
    private DispersionService dispersionService;
 public DispersionController(DispersionService dispersionService){
     this.dispersionService = dispersionService;
 }
 @GetMapping("/dispersion")
    public String getDispersion(Model model){
     List<Dispersion> dispersionList = dispersionService.getDispersion();
     model.addAttribute("dispersion",dispersionList);
     return "Dispersion";
 }
}
