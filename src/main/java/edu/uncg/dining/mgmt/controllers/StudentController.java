/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uncg.dining.mgmt.controllers;


import edu.uncg.dining.mgmt.models.Student;
import edu.uncg.dining.mgmt.models.User;
import edu.uncg.dining.mgmt.repositories.StudentRepository;
import edu.uncg.dining.mgmt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author komalkubsad
 */
@Controller
public class StudentController {
    @Autowired
    private StudentRepository studentRepo;
    
    @Autowired
    private UserRepository userRepository;
    
    @PostMapping("/student")
    public String save(Student student){
                System.out.println("Saving...");
                
                Student existingStudent=studentRepo.findByUsername(student.getUsername());
                Student existingStudentWithId = studentRepo.findOne(student.getStudentId());
                User existingUser=userRepository.findByUsernameAndPassword(student.getUsername(), student.getPassword());
                
                if(existingStudent!=null || existingUser!=null || existingStudentWithId!=null){
                    return "failure";
                }
                
                
                studentRepo.save(student);
                User user = new User();
         user.setUsername(student.getUsername());
         user.setPassword(student.getPassword());
         user.setUsertype("student");
         userRepository.save(user);
                return "success";
    }
    
    @GetMapping("/student")
    public String show (Model model){
        model.addAttribute("student", new Student());
        return "student";
    }

       @GetMapping("/student/{studentId}")
    public String show (Model model,@PathVariable long studentId){
         Student student=studentRepo.findOne(studentId);
        model.addAttribute("student", student);
        return "student";
    }
    
  
    
    
}
