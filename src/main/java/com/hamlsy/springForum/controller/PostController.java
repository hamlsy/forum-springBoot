package com.hamlsy.springForum.controller;


import com.hamlsy.springForum.dto.request.post.PostUpdateRequest;
import com.hamlsy.springForum.dto.request.post.PostUploadRequest;
import com.hamlsy.springForum.dto.response.post.PostListResponse;
import com.hamlsy.springForum.dto.response.post.PostResponse;
import com.hamlsy.springForum.dto.response.post.PostUploadResponse;
import com.hamlsy.springForum.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.NoSuchElementException;


@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @GetMapping("/list")
    public ResponseEntity<Page<PostListResponse>> postList(@RequestParam(value = "page",defaultValue = "0") int page){
        Page<PostListResponse> postList = postService.paging(page);
//
//        int currentPage = postList.getPageable().getPageNumber()+1;
//        int firstPage = Math.max(currentPage-4, 1);
//        int endPage = Math.min(currentPage+9, postList.getTotalPages());

        return new ResponseEntity<>(postList,HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<PostUploadResponse> postUpload(@RequestBody @Valid PostUploadRequest dto){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        PostUploadResponse response = postService.uploadPost(dto, name);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> postDetail(@PathVariable("id") Long id){
        try{
            PostResponse response = postService.findPost(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }


    }

    @GetMapping("/update/{id}")
    public String postModify(@PathVariable("id") Long id, PostResponse postResponse){
        PostResponse request = postService.findPost(id);
        postResponse.setContent(request.getContent());
        postResponse.setSubject(request.getSubject());
        return "post_upload";
    }

    @PostMapping("/update/{id}")
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
