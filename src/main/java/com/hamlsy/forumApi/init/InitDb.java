package com.hamlsy.forumApi.init;

import com.hamlsy.forumApi.domain.Comment;
import com.hamlsy.forumApi.domain.Member;
import com.hamlsy.forumApi.domain.Post;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitDb {
    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.dbInit1();
//        initService.dbInit2();
    }

    @Component
    @RequiredArgsConstructor
    @Transactional
    static class InitService{
        private final EntityManager em;


        public void dbInit1(){
            Member member = new Member();
            member.setName("Lee");
            member.setUserId("lsy4723");
            member.setPassword("1234");
            member.setNickname("hamster");
            em.persist(member);

            Post post1 = Post.createPost(member, "test subject", "test content");
            Post post2 = Post.createPost(member, "두번째 글", "내용은없지롱");
            em.persist(post1);
            em.persist(post2);

            Comment comment1 = Comment.createComment(post1, member, "1빠");
            Comment comment2 = Comment.createComment(post1, member, "2빠");
            Comment comment3 = Comment.createComment(post1, member, "3빠");
            Comment comment4 = Comment.createComment(post2, member, "왜 내용 없냐");

            em.persist(comment1);
            em.persist(comment2);
            em.persist(comment3);
            em.persist(comment4);

        }

        public void dbInit2(){

        }
    }
}

