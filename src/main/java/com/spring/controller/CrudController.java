package com.spring.controller;

import com.spring.entity.Category;
import com.spring.entity.Product;
import com.spring.entity.Users;
import com.spring.repository.CategoryRepository;
import com.spring.repository.ProductRepository;
import com.spring.repository.UsersRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CrudController {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    //users
    @GetMapping("/CRUD/users")
    public String getFormUsers(Model model) {

        model.addAttribute("user", usersRepository.findAll());
        return "CRUD/users";
    }

    @PostMapping("/users/create")
    public String createUsers(Model model,
                              @Valid @ModelAttribute("user") Users user) {
        usersRepository.save(user);
        return "redirect:/CRUD/users";
    }

    @RequestMapping("/users/delete/{id}")
    public String deleteUsers(Model model,
                              @PathVariable(name = "id") Integer id) {
        usersRepository.deleteById(id);
        model.addAttribute("user", usersRepository.findAll());
        return "CRUD/users";
    }

    @GetMapping("/CRUD/users/detail/{id}")
    public String getByIdUsers(Model model,
                               @PathVariable(name = "id") Integer id) {
        usersRepository.findById(id);
        model.addAttribute("user", usersRepository.findById(id).orElse(null));
        return "CRUD/detail_users";
    }

    @PostMapping("/users/update/{id}")
    public String updateUsers(Model model,
                              @PathVariable(name = "id") Integer id,
                              @Valid @ModelAttribute("user") Users users) {
        Users u1 = usersRepository.findById(id).orElse(null);
        users.setId(u1.getId());
        BeanUtils.copyProperties(users, u1);
        usersRepository.save(u1);
        return "redirect:/CRUD/users";
    }

    //category

    @GetMapping("/CRUD/category")
    public String getFormCagetory(Model model) {

        model.addAttribute("categories", categoryRepository.findAll());
        return "/CRUD/category";
    }

    @PostMapping("/category/create")
    public String createCategory(Model model,
                                 @Valid @ModelAttribute("category") Category category) {
        categoryRepository.save(category);
        return "redirect:/CRUD/category";
    }

    @RequestMapping("/category/delete/{id}")
    public String deleteCategory(Model model,
                                 @PathVariable(name = "id") Integer id) {
        categoryRepository.deleteById(id);
        model.addAttribute("category", categoryRepository.findAll());
        return "CRUD/category";
    }

    @GetMapping("/CRUD/category/detail/{id}")
    public String getByIdCategory(Model model,
                                  @PathVariable(name = "id") Integer id) {
        categoryRepository.findById(id);
        model.addAttribute("category", categoryRepository.findById(id).orElse(null));
        return "/CRUD/detail_category";
    }

    @PostMapping("/category/update/{id}")
    public String updateCategory(Model model,
                                 @PathVariable(name = "id") Integer id,
                                 @Valid @ModelAttribute("category") Category category) {
        Category category1 = categoryRepository.findById(id).orElse(null);
        category1.setId(category1.getId());
        BeanUtils.copyProperties(category, category1);
        categoryRepository.save(category1);
        return "redirect:/CRUD/category";
    }

    //product

    @GetMapping("/CRUD/product")
    public String getFormProduct(Model model) {
        model.addAttribute("category", categoryRepository.findAll());
        model.addAttribute("product", productRepository.findAll());
        return "CRUD/product";
    }

    @PostMapping("/product/create")
    public String createProduct(Model model,
                                @Valid @ModelAttribute("product") Product product) {
        productRepository.save(product);
        return "redirect:/CRUD/product";
    }

    @RequestMapping("/product/delete/{id}")
    public String deleteProduct(Model model,
                                @PathVariable(name = "id") Integer id) {
        productRepository.deleteById(id);
        return "redirect:/CRUD/product";
    }

    @GetMapping("/CRUD/product/detail/{id}")
    public String getByIdProduct(Model model,
                                 @PathVariable(name = "id") Integer id) {

        model.addAttribute("product", productRepository.findById(id).orElse(null));
        model.addAttribute("category", categoryRepository.findAll());
        return "CRUD/detail_product";
    }

    @PostMapping("/product/update/{id}")
    public String updateProduct(Model model,
                                @PathVariable(name = "id") Integer id,
                                @Valid @ModelAttribute("product") Product product) {
        Product pd1 = productRepository.findById(id).orElse(null);
        product.setId(pd1.getId());
        BeanUtils.copyProperties(product, pd1);
        productRepository.save(pd1);
        return "redirect:/CRUD/product";
    }
}
