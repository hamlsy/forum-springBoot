package com.hamlsy.springForum.service;

import com.hamlsy.springForum.domain.Comment;
import com.hamlsy.springForum.domain.Member;
import com.hamlsy.springForum.domain.Post;
import com.hamlsy.springForum.dto.request.comment.CommentWriteRequest;
import com.hamlsy.springForum.dto.response.CommentWriteResponse;
import com.hamlsy.springForum.repository.CommentRepository;
import com.hamlsy.springForum.repository.MemberRepository;
import com.hamlsy.springForum.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

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
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new NoSuchElementException()
        );
        Comment comment = CommentWriteRequest.toEntity(request);
        comment.setMember(member);
        comment.setPost(post);
        commentRepository.save(comment);
        return CommentWriteResponse.fromEntity(comment);
    }

    @Transactional
    public void deleteComment(Long commentId){
        commentRepository.deleteById(commentId);
    }

    public Comment findComment(Long id){
        return commentRepository.findById(id).orElseThrow(
                //Todo 예외 생성
                () -> new NoSuchElementException()
        );
    }

    public List<Comment> findAllComment(){
        return commentRepository.findAll();
    }

    @Transactional
    public CommentWriteResponse modifyComment(Long commentId, CommentWriteRequest request){
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new NoSuchElementException()
        );
        comment.update(request.getContent());
        return CommentWriteResponse.fromEntity(comment);
    }
}
