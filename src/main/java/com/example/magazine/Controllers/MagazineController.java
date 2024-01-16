package com.example.magazine.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MagazineController {
    @GetMapping("/magazine")
    public String getMagazine(Model model){

        return "magazine";
    }
}
