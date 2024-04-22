package com.hamlsy.springForum.controller;

import com.hamlsy.springForum.dto.request.post.PostRequest;
import com.hamlsy.springForum.dto.request.post.PostUpdateRequest;
import com.hamlsy.springForum.dto.request.post.PostUploadRequest;
import com.hamlsy.springForum.dto.response.post.PostListResponse;
import com.hamlsy.springForum.dto.response.post.PostResponse;
import com.hamlsy.springForum.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @GetMapping("/list")
    public String postList(@RequestParam(value = "page",defaultValue = "0") int page, Model model){
        Page<PostListResponse> postList = postService.paging(page);

        int currentPage = postList.getPageable().getPageNumber()+1;
        int firstPage = Math.max(currentPage-4, 1);
        int endPage = Math.min(currentPage+9, postList.getTotalPages());

        model.addAttribute("postList", postList);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("firstPage", firstPage);
        model.addAttribute("endPage", endPage);
        return "board";
    }

    @GetMapping("/upload")
    public String postUpload(Model model){
        model.addAttribute("postResponse", new PostResponse());
        return "post_upload";
    }

    @PostMapping("/upload")
    public String postUpload(@Valid PostUploadRequest dto, Principal principal){
        postService.uploadPost(dto, principal);
        return "redirect:/post/list?page=0";
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
        postService.updatePost(postId, request);
        return String.format("redirect:/post/%s", postId);
    }

    @GetMapping("/delete/{id}")
    public String postDelete(@PathVariable("id") Long postId, Principal principal){
        postService.deletePost(postId, principal);
        return "redirect:/post/list";
    }

}
