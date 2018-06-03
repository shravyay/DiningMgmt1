/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uncg.dining.mgmt.controllers;
import edu.uncg.dining.mgmt.models.Employee;
import edu.uncg.dining.mgmt.repositories.EmployeeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
/**
 *
 * @author komalkubsad
 */
@Controller
public class EmployeeController{
    
    @Autowired
    private EmployeeRepository employeeRepo;
    
    @PostMapping("/employee")
    public String save(Employee employee){
                System.out.println("Saving...");
                employeeRepo.save(employee);
                return "redirect:/employees";        
    }
    
    @GetMapping("/employee")
    public String show (Model model){
        model.addAttribute("employee", new Employee());
        return "employee";
    }
     @GetMapping("/employees")
    public String showAllEmployees (Model model){ 
        final List<Employee> allEmployees = employeeRepo.findAll();
        model.addAttribute("employees",allEmployees);
        return "manager_home";
    }
    
}
