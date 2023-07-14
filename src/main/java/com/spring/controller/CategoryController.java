package com.spring.controller;

import com.spring.entity.Category;
import com.spring.entity.Product;
import com.spring.repository.CategoryRepository;
import com.spring.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/category/{id}/product")
    public String getALlProductByCategory(Model model,
                                          @PathVariable Integer id) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);

        List<Product> products = productRepository.findAllByCategoryId(id);
        model.addAttribute("products", products);
        return "category/detail";
    }
}
