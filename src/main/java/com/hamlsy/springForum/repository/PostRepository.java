package com.hamlsy.springForum.repository;

import com.hamlsy.springForum.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAll(Pageable pageable);

    @Query("SELECT p FROM Post p JOIN FETCH p.member")
    Page<Post> findAllWithMember(Pageable pageable);
}
