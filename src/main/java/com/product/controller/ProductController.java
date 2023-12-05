package com.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.product.entity.Product;
import com.product.repo.ProductRepository;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductRepository repo;
    @GetMapping("/delete")
    public String deleteProduct(@RequestParam Integer pid,Model m) {
    	repo.deleteById(pid);
    	m.addAttribute("msg","deleted...");
    	m.addAttribute("products", repo.findAll());
    	
    	return "table";
    }

    @GetMapping("/products")
    public String loadProducts(Model m) {
        // Retrieve a list of products from the repository
        List<Product> products = repo.findAll();
        
        // Add the list of products to the model
        m.addAttribute("products", products);
        
        // Return the view name "table"
        return "table";
    }

    @GetMapping("/")
    public String loadForm(Model model) {
        // Add a new Product object to the model
        model.addAttribute("p", new Product());
        
        // Return the view name "index"
        return "index";
    }

    @PostMapping("/product")
    public String saveForm(@ModelAttribute("p") Product p, Model m) {
        // Save the product to the repository
        p = repo.save(p);
        
        // Add a "success" message to the model
        m.addAttribute("msg", "success");
        
        // Return the view name "index"
        return "index";
    }
}
