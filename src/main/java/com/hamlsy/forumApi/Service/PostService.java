package com.hamlsy.forumApi.Service;

import com.hamlsy.forumApi.domain.Post;
import com.hamlsy.forumApi.dto.request.post.PostUpdateDto;
import com.hamlsy.forumApi.dto.request.post.PostUploadDto;
import com.hamlsy.forumApi.dto.response.PostResponse;
import com.hamlsy.forumApi.dto.response.PostUpdateResponse;
import com.hamlsy.forumApi.dto.response.PostUploadResponse;
import com.hamlsy.forumApi.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public PostUploadResponse uploadPost(PostUploadDto dto){
        Post post = dto.toEntity(dto);
        postRepository.save(post);
        return PostUploadResponse.fromEntity(post);
    }

    @Transactional
    public PostUpdateResponse updatePost(Long postId, PostUpdateDto dto){
        Post updatePost = postRepository.findById(postId);
        updatePost.update(dto.getSubject(), dto.getContent());
        return PostUpdateResponse.fromEntity(updatePost);
    }

    @Transactional
    public void deletePost(Long postId){
        postRepository.delete(postId);
    }

    public PostResponse findPost(Long id){
        Post post = postRepository.findById(id);
        return PostResponse.fromEntity(post);
    }

    public List<PostResponse> findAllPost(){
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(p -> PostResponse.fromEntity(p))
                .collect(Collectors.toList());
    }


}
