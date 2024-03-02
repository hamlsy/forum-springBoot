package com.hamlsy.forumApi.repository;

import com.hamlsy.forumApi.domain.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private final EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    public Member findById(Long id){
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){
        return em.createQuery(
                "select m from Member m", Member.class
        ).getResultList();
    }

    public Member findOneByUserId(String userId){
        return em.createQuery(
                "select m from Member m" +
                        " where m.userId = :userId", Member.class
        ).setParameter("userId", userId).getSingleResult();
    }

    //이름 중복 검증을 위한 findByName
//    public List<Member> findByName(String name){
//        return em.createQuery(
//                "select m from Member m" +
//                        " where m.name = :name", Member.class
//        ).setParameter("name", name).getResultList();
//    }

    //회원 탈퇴
    public void removeMember(Member member){
        em.remove(member);
    }

    //아이디 중복 검증을 위한 findByUserId
    public List<Member> findByUserId(String userId){
        return em.createQuery(
                "select m from Member m" +
                        " where m.userId = :userId", Member.class
        ).setParameter("userId", userId).getResultList();
    }


}
