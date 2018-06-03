/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uncg.dining.mgmt.controllers;

import edu.uncg.dining.mgmt.models.Customize;
import edu.uncg.dining.mgmt.models.Student;
import edu.uncg.dining.mgmt.models.User;
import edu.uncg.dining.mgmt.repositories.CustomizeRepository;
import edu.uncg.dining.mgmt.repositories.StudentRepository;
import java.math.BigInteger;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
public class CustomizeController {
    
    @Autowired
    private CustomizeRepository customizeRepo;
    
    @Autowired
    private StudentRepository studentRepository;
    
    @PostMapping("/customize")
    public String save(Customize customize, @AuthenticationPrincipal User user){
        Student s= studentRepository.findByUsername(user.getUsername());
                System.out.println("Saving..."+customize.getItem()+" "+customize.getDayOfSpecial());
                customizeRepo.save(customize);                        
                 return "redirect:/customized/"+s.getStudentId();        
    }
    
    @GetMapping("/customize")
    public String show (Model model, @AuthenticationPrincipal User user){
        Student s= studentRepository.findByUsername(user.getUsername());
        Customize c = new Customize();
        c.setStudentId(s.getStudentId());
        c.setStudentName(s.getStudentName());
        model.addAttribute("customize", c);
        return "customize";
    }
    
       @GetMapping("/customize/{customizeId}")
    public String show (Model model,@PathVariable long customizeId){
        Customize customize=customizeRepo.findOne(customizeId);
        model.addAttribute("customize", customize);
        return "customize";
    }
    
    @GetMapping("/customizations")
    public String showAllCustomizations(Model model){
         final List<Customize> allCustomized = customizeRepo.findAll();
        model.addAttribute("customized",allCustomized);
        return "all_customizations";
    }
    
    
    @GetMapping("/customized/{studentId}")
    public String showAllCustomized (Model model, @PathVariable("studentId") Long studentId){ 
        final List<Customize> allCustomized = customizeRepo.findByStudentId(studentId);
        model.addAttribute("customized",allCustomized);
        return "student_home";
    }
}
