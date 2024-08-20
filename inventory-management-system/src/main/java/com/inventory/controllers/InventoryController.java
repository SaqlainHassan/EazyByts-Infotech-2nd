package com.inventory.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inventory.model.Inventory;
import com.inventory.services.InventoryService;

@Controller
@RequestMapping("/inventory")
public class InventoryController {
	
	 	@Autowired
	    private InventoryService inventoryService;

	    @GetMapping
	    public String listInventory(Model model) {
	        model.addAttribute("inventory", inventoryService.getAllInventory());
	        return "inventory_dashboard";
	    }

	    @GetMapping("/new")
	    public String showNewInventoryForm(Model model) {
	        model.addAttribute("inventory", new Inventory());
	        return "inventory_form";
	    }

	    @PostMapping
	    public String saveInventory(@ModelAttribute("inventory") Inventory inventory) {
	        inventoryService.saveInventory(inventory);
	        return "redirect:/inventory";
	    }

	    @GetMapping("/edit/{id}")
	    public String showEditInventoryForm(@PathVariable Long id, Model model) {
	        model.addAttribute("inventory", inventoryService.getInventoryById(id));
	        return "inventory_form";
	    }

	    @GetMapping("/delete/{id}")
	    public String deleteInventory(@PathVariable Long id) {
	        inventoryService.deleteInventory(id);
	        return "redirect:/inventory";
	    }

}
