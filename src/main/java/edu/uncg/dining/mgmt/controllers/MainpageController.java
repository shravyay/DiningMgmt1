/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uncg.dining.mgmt.controllers;

import edu.uncg.dining.mgmt.models.Customize;
import edu.uncg.dining.mgmt.models.Interview;
import edu.uncg.dining.mgmt.models.Menu;
import edu.uncg.dining.mgmt.repositories.CustomizeRepository;
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
public class MainpageController {
  
    
    @GetMapping("/")
    public String show (Model model){
        model.addAttribute("menu", new Menu());
        return "mainpage";
    }
    
  
    
}
