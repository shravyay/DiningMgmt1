/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uncg.dining.mgmt.controllers;

import edu.uncg.dining.mgmt.models.Stock;
import edu.uncg.dining.mgmt.repositories.MenuRepository;
import edu.uncg.dining.mgmt.repositories.StockRepository;
import java.util.List;
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
public class StockController {
    @Autowired
    private StockRepository stockRepo;
    
    @PostMapping("/stock")
    public String save(Stock stock){
                System.out.println("Saving...");
                stockRepo.save(stock);
                 return "redirect:/stocks";       
                        
                        
    }
    
    @GetMapping("/stock")
    public String show (Model model){
        model.addAttribute("stock", new Stock());
        return "stock";
    }

       @GetMapping("/stock/{itemId}")
    public String show (Model model,@PathVariable long itemId){
         Stock stock=stockRepo.findOne(itemId);
        model.addAttribute("stock", stock);
        return "employee";
    }
    
    @GetMapping("/stocks")
    public String showAllStocks (Model model){ 
        final List<Stock> allStocks = stockRepo.findAll();
        model.addAttribute("stocks",allStocks);
        return "stock_display";
    }
    
    
}
