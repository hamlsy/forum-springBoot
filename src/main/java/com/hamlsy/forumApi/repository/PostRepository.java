package com.hamlsy.forumApi.repository;

import com.hamlsy.forumApi.domain.Post;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository {
    private final EntityManager em;

    public void save(Post post){
        em.persist(post);
    }

    public void delete(Long postId){
        em.remove(findById(postId));
    }

    public Post findById(Long id){
        return em.find(Post.class, id);
    }

    public List<Post> findAll(){
        return em.createQuery(
                "select p from Post p", Post.class
        ).getResultList();
    }




}
