package com.hamlsy.forumApi.controller;

import com.hamlsy.forumApi.dto.response.PostResponse;
import com.hamlsy.forumApi.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @GetMapping("/list")
    public String postList(Model model){
        List<PostResponse> postList = postService.findAllPost();
        model.addAttribute("postList", postList);
        return "board";
    }
}
