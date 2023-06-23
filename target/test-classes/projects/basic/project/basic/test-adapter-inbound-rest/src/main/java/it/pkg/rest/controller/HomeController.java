package it.pkg.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String swagger() {
        return "redirect:swagger-ui.html";
    }
}
