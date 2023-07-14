package com.spring.controller;

import com.spring.entity.Users;
import com.spring.repository.UsersRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    @Autowired
    HttpServletRequest request;

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/login")
    public String getLoginForm(Model model) {

        model.addAttribute("user", new Users());
        return "login";
    }

    @PostMapping("/login")
    public String checkLogin(Model model,
                             @Valid @ModelAttribute("user") Users user,
                             BindingResult result,
                             HttpSession session,
                             RedirectAttributes attributes) {
        //validate
        if (result.hasErrors()) {
            return "login";
        }

        //login success
        Users userFromDB = usersRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (userFromDB != null) {
            session.setAttribute("userLogged", userFromDB);
            return "redirect:/product/list";
        }

        //login failed
        model.addAttribute("message", "Sai thông tin");
        return "login";

    }

}
