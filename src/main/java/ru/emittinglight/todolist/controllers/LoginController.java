package ru.emittinglight.todolist.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.emittinglight.todolist.represent.UserRepresent;
import ru.emittinglight.todolist.service.UserService;

import javax.validation.Valid;


@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new UserRepresent());
        return "register";
    }

    @PostMapping("/register")
    public String registerNewUser(@ModelAttribute("user") @Valid UserRepresent userRepresent,
                                  BindingResult result) {
        logger.info("Now user {}", userRepresent);
        if (result.hasErrors()) {
            return "register";
        }
        if (!userRepresent.getPassword().equals(userRepresent.getMatchingPassword())) {
            result.rejectValue("password", "", "Пароль не совпадает");
            return "register";
        }
        userService.create(userRepresent);
        return "redirect:login";
    }
}
