package com.hamlsy.springForum.repository;

import com.hamlsy.springForum.domain.Comment;
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

    public void delete(Long commentId){
        em.createQuery(
                "delete from Comment c " +
                        "where c.id = :commentId", Comment.class)
                .setParameter("commentId", commentId);
    }

    public Comment findById(Long id){
        return em.find(Comment.class,id);
    }

    public List<Comment> findAllByUserId(String userId){
        return em.createQuery(
                "select c from Comment c " +
                        "where c.member.userId = :userId"
                                ,Comment.class)
                .setParameter("userId", userId)
                .getResultList();

    }

    public List<Comment> findAll(){
        return em.createQuery(
                "select c from Comment c",Comment.class
        ).getResultList();
    }
}
