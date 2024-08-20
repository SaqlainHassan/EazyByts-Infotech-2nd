package com.inventory.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inventory.model.Order;
import com.inventory.services.OrderService;

@Controller
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
    private OrderService orderService;

    @GetMapping
    public String listOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "order_list";
    }

    @GetMapping("/new")
    public String showNewOrderForm(Model model) {
        model.addAttribute("order", new Order());
        return "order_form";
    }

    @PostMapping
    public String saveOrder(@ModelAttribute("order") Order order) {
        orderService.saveOrder(order);
        return "redirect:/orders";
    }

    @GetMapping("/edit/{id}")
    public String showEditOrderForm(@PathVariable Long id, Model model) {
        model.addAttribute("order", orderService.getOrderById(id));
        return "order_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return "redirect:/orders";
    }

}
