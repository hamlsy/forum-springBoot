package com.hamlsy.forumApi.repository;

import com.hamlsy.forumApi.domain.Comment;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepository {
    private final EntityManager em;

    public void save(Comment comment){
        em.persist(comment);
    }

    public void delete(Comment comment){
        em.remove(comment);
    }

    public Comment findById(Long id){
        return em.find(Comment.class,id);
    }

    public List<Comment> findAll(){
        return em.createQuery(
                "select c from Comment c",Comment.class
        ).getResultList();
    }
}
