package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
@Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public String getUserPage(Principal principal, Model model) {
        Optional<User> userOptional = userService.findUserByUsername(principal.getName());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            model.addAttribute("user", user);
            return "user";
        } else {
            return "redirect:/login";
        }
    }
}
