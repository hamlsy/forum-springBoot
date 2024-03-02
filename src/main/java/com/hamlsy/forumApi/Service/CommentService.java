package com.hamlsy.forumApi.Service;

import com.hamlsy.forumApi.domain.Comment;
import com.hamlsy.forumApi.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {
    private final CommentRepository commentRepository;

    @Transactional
    public void saveComment(Comment comment){
        commentRepository.save(comment);
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
