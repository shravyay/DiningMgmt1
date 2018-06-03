/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uncg.dining.mgmt.controllers;

import edu.uncg.dining.mgmt.models.Employee;
import edu.uncg.dining.mgmt.models.Payroll;
import edu.uncg.dining.mgmt.repositories.PayrollRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author shrav
 */
@Controller
public class PayrollController {
    @Autowired
    private PayrollRepository payrollRepo;
    
    @PostMapping("/payroll")
    public String save(Payroll payroll){
                System.out.println("Saving...");
                payrollRepo.save(payroll);  
                return "redirect:/payrolls";
                        
    }
    
    @GetMapping("/payroll")
    public String show (Model model){
        model.addAttribute("payroll", new Payroll());
        return "payroll";
    }
    
       @GetMapping("/payroll/{payrollId}")
    public String show (Model model,@PathVariable long payrollId){
        Payroll payroll=payrollRepo.findOne(payrollId);
        model.addAttribute("payroll", payroll);
        return "payroll";
    }
  @GetMapping("/payrolls")
    public String showAllPayrolls (Model model){ 
        final List<Payroll> allPayrolls = payrollRepo.findAll();
        model.addAttribute("payrolls",allPayrolls);
        return "payroll_display";
}
}
