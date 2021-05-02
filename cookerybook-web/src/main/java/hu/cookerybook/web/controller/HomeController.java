package hu.cookerybook.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping({"/", "/index"})
    public String index(@RequestParam(value = "name", defaultValue = "Andris", required = true) String name, Model model) {
        model.addAttribute("name", name);
        return "index";
    }

}
