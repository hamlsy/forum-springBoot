package com.hamlsy.forumApi.service;

import com.hamlsy.forumApi.domain.Comment;
import com.hamlsy.forumApi.domain.Member;
import com.hamlsy.forumApi.domain.MemberRole;
import com.hamlsy.forumApi.domain.Post;
import com.hamlsy.forumApi.dto.request.comment.CommentWriteRequest;
import com.hamlsy.forumApi.dto.response.CommentWriteResponse;
import com.hamlsy.forumApi.repository.CommentRepository;
import com.hamlsy.forumApi.repository.MemberRepository;
import com.hamlsy.forumApi.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    @Transactional
    public CommentWriteResponse writeComment(CommentWriteRequest request, Long postId, Principal principal){
        Member member = memberRepository.findByUserId(principal.getName());
        Post post = postRepository.findById(postId);
        Comment comment = CommentWriteRequest.toEntity(request);
        comment.setMember(member);
        comment.setPost(post);
        commentRepository.save(comment);
        return CommentWriteResponse.fromEntity(comment);
    }

    @Transactional
    public void deleteComment(Comment comment){
        commentRepository.delete(comment);
    }

    public Comment findComment(Long id){
        return commentRepository.findById(id);
    }

    public List<Comment> findAllComment(){
        return commentRepository.findAll();
    }

}
