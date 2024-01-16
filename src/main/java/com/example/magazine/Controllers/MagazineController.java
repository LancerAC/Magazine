package com.example.magazine.Controllers;

import com.example.magazine.Entity.Post;
import com.example.magazine.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MagazineController {

    private final PostRepository postRepository;

    @GetMapping("/magazine")
    public String getMagazine(Model model){
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("postList", posts);
        return "magazine";
    }
    @GetMapping("/magazine/add")
    public String addMagazine(Model model){

        return "magazine-add";
    }
}
