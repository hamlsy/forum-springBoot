package com.hamlsy.forumApi.controller;

import com.hamlsy.forumApi.dto.request.post.PostRequest;
import com.hamlsy.forumApi.dto.request.post.PostUpdateRequest;
import com.hamlsy.forumApi.dto.request.post.PostUploadRequest;
import com.hamlsy.forumApi.dto.response.post.PostListResponse;
import com.hamlsy.forumApi.dto.response.post.PostResponse;
import com.hamlsy.forumApi.dto.response.post.PostUpdateResponse;
import com.hamlsy.forumApi.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @GetMapping("/list")
    public String postList(Model model){
        List<PostListResponse> postList = postService.findAllPost();
        model.addAttribute("postList", postList);
        return "board";
    }

    @GetMapping("/upload")
    public String postUpload(){
        return "post_upload";
    }

    @PostMapping("/upload")
    public String postUpload(PostUploadRequest dto, Principal principal){
        postService.uploadPost(dto, principal);
        return "redirect:/post/list";
    }

    @GetMapping("/{id}")
    public String postDetail(@PathVariable("id") Long id, Model model){
        PostResponse response = postService.findPost(id);
        model.addAttribute("post", response);
        return "post_detail";
    }

    @GetMapping("/modify/{id}")
    public String postModify(@PathVariable("id") Long id, PostResponse postResponse){
        PostResponse request = postService.findPost(id);
        postResponse.setContent(request.getContent());
        postResponse.setSubject(request.getSubject());
        return "post_upload";
    }

    @PostMapping("/modify/{id}")
    public String postModify(@PathVariable("id") Long postId, PostUpdateRequest request){
        //bindingResult처리 필요
        postService.updatePost(postId, request);
        return String.format("redirect:/post/%s", postId);
    }

}
