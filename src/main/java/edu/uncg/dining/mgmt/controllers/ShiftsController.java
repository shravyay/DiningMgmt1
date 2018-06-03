/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uncg.dining.mgmt.controllers;

import edu.uncg.dining.mgmt.models.Employee;
import edu.uncg.dining.mgmt.models.Shifts;
import edu.uncg.dining.mgmt.repositories.EmployeeRepository;
import edu.uncg.dining.mgmt.repositories.ShiftsRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author shrav
 */
  @Controller
public class ShiftsController {
  
    @Autowired
    private ShiftsRepository shiftsRepo;
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @PostMapping("/shifts")
    public String save(Shifts shifts){
                System.out.println("Saving...");
                shiftsRepo.save(shifts);                        
                return "redirect:/shiftss";        
    }
    
    @GetMapping("/shifts")
    public String show (Model model){
        model.addAttribute("shifts", new Shifts());
        return "shifts";
    }
    

       @GetMapping("/shifts/{shiftsId}")
    public String show (Model model,@PathVariable long shiftId){
        Shifts shifts=shiftsRepo.findOne(shiftId);
        model.addAttribute("shifts", shifts);
        return "shifts";
    }
    
    @GetMapping("/shiftss")
    public String showAllShiftss (Model model){ 
        final List<Shifts> allShiftss = shiftsRepo.findAll();
        model.addAttribute("shiftss",allShiftss);
        return "supervisor_home";
    }
    
    @ModelAttribute("employees")
    public List<String> getAllEmployees(){
       List<String> emp = new ArrayList<>();
              List<Employee> allEmployees=  employeeRepository.findAll();
              for(Employee employee:allEmployees){
                  emp.add(employee.getEmployeeId() +":"+employee.getEmployeeName());
              }
              return emp;
    }
}
