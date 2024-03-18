package com.hamlsy.forumApi.api;
import com.hamlsy.forumApi.dto.response.post.PostListResponse;
import com.hamlsy.forumApi.service.PostService;
import com.hamlsy.forumApi.dto.request.post.PostUpdateRequest;
import com.hamlsy.forumApi.dto.request.post.PostUploadRequest;
import com.hamlsy.forumApi.dto.response.post.PostResponse;
import com.hamlsy.forumApi.dto.response.post.PostUpdateResponse;
import com.hamlsy.forumApi.dto.response.post.PostUploadResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/postApi")
public class PostApiController {
    private final PostService postService;

    @GetMapping("/list")
    public List<PostListResponse> postList(){
        List<PostListResponse> responses = postService.findAllPost();
        return responses;
    }

    @GetMapping("/{id}")
    public PostResponse post(@PathVariable("id") Long id){
        //순환참조 해결 필요
        PostResponse response = postService.findPost(id);
        return response;
    }

    @PostMapping("/upload")
    public PostUploadResponse uploadPost(@RequestBody @Valid PostUploadRequest dto, Principal principal){;
        return postService.uploadPost(dto, principal);
    }

    @PostMapping("/{id}/delete")
    public Long deletePost(@PathVariable("id") Long postId){
        postService.deletePost(postId);
        return postId;
    }

    @PostMapping("/{id}/update")
    public PostUpdateResponse updatePost(@PathVariable("id") Long postId , @RequestBody PostUpdateRequest dto){
        PostUpdateResponse response = postService.updatePost(postId, dto);
        return response;
    }

}
