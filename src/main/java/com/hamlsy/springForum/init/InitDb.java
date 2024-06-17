package com.hamlsy.springForum.init;

import com.hamlsy.springForum.domain.Comment;
import com.hamlsy.springForum.domain.Member;
import com.hamlsy.springForum.domain.MemberRole;
import com.hamlsy.springForum.domain.Post;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class InitDb {
    private final InitService initService;

    @PostConstruct
    public void init(){
//        initService.dbInit1();
//        initService.dbInit2();
        initService.dbInitLoop(80);

    }

    @Component
    @RequiredArgsConstructor
    @Transactional
    static class InitService{
        private final EntityManager em;
        private final PasswordEncoder passwordEncoder;
        public void dbInit1(){
            Member member = new Member();
            member.setName("Lee");
            member.setUserId("lsy4723");
            member.setPassword(passwordEncoder.encode("1234"));
            member.setNickname("hamster");
            member.setRole(MemberRole.MEMBER);
            em.persist(member);

            Post post1 = Post.createPost(member, "test subject", "test content", LocalDateTime.now());
            Post post2 = Post.createPost(member, "두번째 글", "내용", LocalDateTime.now());
            em.persist(post1);
            em.persist(post2);

            Comment comment1 = Comment.createComment(post1, member, "comment1");
            Comment comment2 = Comment.createComment(post1, member, "comment2");
            Comment comment3 = Comment.createComment(post1, member, "comment3");
            Comment comment4 = Comment.createComment(post2, member, "comment11");

            em.persist(comment1);
            em.persist(comment2);
            em.persist(comment3);
            em.persist(comment4);

        }

        public void dbInit2(){
            Member member = new Member();
            member.setName("dd");
            member.setUserId("dd");
            member.setPassword(passwordEncoder.encode("dd"));
            member.setNickname("dd");
            member.setRole(MemberRole.MEMBER);
            em.persist(member);

            Post post1 = Post.createPost(member, "제목제목", "내용내용", LocalDateTime.now());
            em.persist(post1);

            Comment comment1 = Comment.createComment(post1, member, "comment1");
            Comment comment2 = Comment.createComment(post1, member, "comment2");
            Comment comment3 = Comment.createComment(post1, member, "comment3");

            em.persist(comment1);
            em.persist(comment2);
            em.persist(comment3);


        }

        public void dbInit3(int i){
            Member member = new Member();
            member.setName("member" + i);
            member.setUserId("user" + i);
            member.setPassword(passwordEncoder.encode("ps" + i));
            member.setNickname("member"+i);
            member.setRole(MemberRole.MEMBER);
            em.persist(member);

            Post post1 = Post.createPost(member, "제목" + i, "내용" + i, LocalDateTime.now());
            em.persist(post1);

            Comment comment1 = Comment.createComment(post1, member, "comment1");
            Comment comment2 = Comment.createComment(post1, member, "comment2");
            Comment comment3 = Comment.createComment(post1, member, "comment3");

            em.persist(comment1);
            em.persist(comment2);
            em.persist(comment3);
        }
        public void dbInitLoop(int count){
            for(int i = 1 ; i <= count ; i++){
                dbInit3(i);
            }
        }
    }
}

