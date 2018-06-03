/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uncg.dining.mgmt.controllers;

import edu.uncg.dining.mgmt.models.Interview;
import edu.uncg.dining.mgmt.repositories.InterviewRepository;
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
public class InterviewController {
   
    @Autowired
    private InterviewRepository interviewRepo;
    
    @PostMapping("/interview")
    public String save(Interview interview){
                System.out.println("Saving...");
                interviewRepo.save(interview);                        
                return "redirect:/interviews";         
    }
    
    @GetMapping("/interview")
    public String show (Model model){
        model.addAttribute("interview", new Interview());
        return "interview";
    }
    
       @GetMapping("/interview/{interviewId}")
    public String show (Model model,@PathVariable long interviewId){
        Interview interview=interviewRepo.findOne(interviewId);
        model.addAttribute("interview", interview);
        return "interview";
    }
   
        @GetMapping("/interviews")
    public String showAllInterviews (Model model){ 
        final List<Interview> allInterviews = interviewRepo.findAll();
        model.addAttribute("Interviews",allInterviews);
        return "interview_display";
    }
    
}

