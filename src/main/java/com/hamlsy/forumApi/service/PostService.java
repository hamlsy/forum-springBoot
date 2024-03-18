package com.hamlsy.forumApi.service;

import com.hamlsy.forumApi.domain.Member;
import com.hamlsy.forumApi.domain.Post;
import com.hamlsy.forumApi.dto.request.post.PostUpdateRequest;
import com.hamlsy.forumApi.dto.request.post.PostUploadRequest;
import com.hamlsy.forumApi.dto.response.post.PostListResponse;
import com.hamlsy.forumApi.dto.response.post.PostResponse;
import com.hamlsy.forumApi.dto.response.post.PostUpdateResponse;
import com.hamlsy.forumApi.dto.response.post.PostUploadResponse;
import com.hamlsy.forumApi.repository.MemberRepository;
import com.hamlsy.forumApi.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    @Transactional
    public PostUploadResponse uploadPost(PostUploadRequest dto, Principal principal){
        dto.setPostTime(LocalDateTime.now());
        Post post = dto.toEntity(dto);
        Member member = memberRepository.findByUserId(principal.getName());
        post.setMember(member);
        postRepository.save(post);
        return PostUploadResponse.fromEntity(post);
    }

    @Transactional
    public PostUpdateResponse updatePost(Long postId, PostUpdateRequest dto){
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

    public List<PostListResponse> findAllPost(){
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(p -> PostListResponse.fromEntity(p))
                .collect(Collectors.toList());
    }


}
