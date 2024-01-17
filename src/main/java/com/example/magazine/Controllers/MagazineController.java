package com.example.magazine.Controllers;

import com.example.magazine.Entity.Post;
import com.example.magazine.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
    @PostMapping("/magazine/add")
    public String postMagazine(@RequestParam String title,
                               @RequestParam String anons,
                               @RequestParam String text){
        Post post = Post
                .builder()
                .title(title)
                .anons(anons)
                .full_text(text)
                .build();

        postRepository.save(post);
        return "home";
    }

    @GetMapping("/magazine/{id}")
    public String magazineDetails(@PathVariable(value = "id") Long postId, Model model){
        if(!postRepository.existsById(postId)){
            return "home";
        }
        Iterable<Post> post = postRepository.findAllById(Collections.singleton(postId));
        model.addAttribute("post", post);
        return "magazine-details";
    }
    @GetMapping("/magazine/{id}/edit")
    public String magazineEdit(@PathVariable(value = "id") Long postId, Model model){
        if(!postRepository.existsById(postId)){
            return "home";
        }
        Iterable<Post> post = postRepository.findAllById(Collections.singleton(postId));
        model.addAttribute("post", post);
        return "magazine-edit";
    }

    @PostMapping("/magazine/{id}/edit")
    public String changeMagazine(@PathVariable(value = "id") Long id,
                               @RequestParam String title,
                               @RequestParam String anons,
                               @RequestParam String text){
        Post post = postRepository.findById(id).orElseThrow();
        post.setTitle(title);
        post.setAnons(anons);
        post.setFull_text(text);
        postRepository.save(post);
        return "home";
    }


}
