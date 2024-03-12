package com.hamlsy.forumApi.api;
import com.hamlsy.forumApi.service.PostService;
import com.hamlsy.forumApi.dto.request.post.PostUpdateDto;
import com.hamlsy.forumApi.dto.request.post.PostUploadDto;
import com.hamlsy.forumApi.dto.response.PostResponse;
import com.hamlsy.forumApi.dto.response.PostUpdateResponse;
import com.hamlsy.forumApi.dto.response.PostUploadResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/postApi")
public class PostApiController {
    private final PostService postService;

    @GetMapping("/list")
    public List<PostResponse> postList(){
        List<PostResponse> responses = postService.findAllPost();
        return responses;
    }

    @GetMapping("/{id}")
    public PostResponse post(@PathVariable("id") Long id){
        PostResponse response = postService.findPost(id);
        return response;
    }

    @PostMapping("/upload")
    public PostUploadResponse uploadPost(@RequestBody @Valid PostUploadDto dto){;
        return postService.uploadPost(dto);
    }

    @PostMapping("/{id}/delete")
    public Long deletePost(@PathVariable("id") Long postId){
        postService.deletePost(postId);
        return postId;
    }

    @PostMapping("/{id}/update")
    public PostUpdateResponse updatePost(@PathVariable("id") Long postId , @RequestBody PostUpdateDto dto){
        PostUpdateResponse response = postService.updatePost(postId, dto);
        return response;
    }

}
