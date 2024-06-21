package com.hamlsy.springForum.service;

import com.hamlsy.springForum.domain.Member;
import com.hamlsy.springForum.domain.Post;
import com.hamlsy.springForum.dto.request.post.PostUpdateRequest;
import com.hamlsy.springForum.dto.request.post.PostUploadRequest;
import com.hamlsy.springForum.dto.response.post.PostListResponse;
import com.hamlsy.springForum.dto.response.post.PostResponse;
import com.hamlsy.springForum.dto.response.post.PostUpdateResponse;
import com.hamlsy.springForum.dto.response.post.PostUploadResponse;
import com.hamlsy.springForum.repository.MemberRepository;
import com.hamlsy.springForum.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public PostUploadResponse uploadPost(PostUploadRequest dto, String userId){
        dto.setPostTime(LocalDateTime.now());
        Post post = dto.toEntity(dto);
        Member member = memberRepository.findByUserId(userId);
        post.setMember(member);
        postRepository.save(post);
        return PostUploadResponse.fromEntity(post);
    }

    @Transactional
    public PostUpdateResponse updatePost(Long postId, PostUpdateRequest dto) throws AccessDeniedException{
        Post post = findPostById(postId);

        //사용자와 다를 경우
        memberValidation(post.getMember().getUserId());

        post.update(dto.getSubject(), dto.getContent());
        return PostUpdateResponse.fromEntity(post);
    }

    @Transactional
    public void deletePost(Long postId, Principal principal){
        if(principal == null){
            throw new IllegalStateException("인증이 필요합니다.");
        }
        Post post = findPostById(postId);
        if(post.getMember().getUserId() != principal.getName()){
            throw new IllegalStateException("작성자만 삭제할 수 있습니다.");
        }
        postRepository.deleteById(postId);
    }

    public PostResponse findPost(Long postId){
        Post post = findPostById(postId);
        return PostResponse.fromEntity(post);
    }

    private void memberValidation(String userId) throws AccessDeniedException{
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        //사용자와 다를 경우
        if(!userId.equals(name)){
            throw new AccessDeniedException("접근 권한이 없습니다.");
        }
    }

    public PostResponse findUpdatePost(Long postId) throws AccessDeniedException{
        Post post = findPostById(postId);
        //사용자와 다를 경우
        memberValidation(post.getMember().getUserId());
        return PostResponse.fromEntity(post);
    }

    private Post findPostById(Long id){
        return postRepository.findById(id).orElseThrow(
                //todo: 예외 클래스 새로 작성
                () -> new NoSuchElementException()
        );
    }

    public Page<PostListResponse> paging(int page){
        int pageLimit = 15;
        Pageable pageable = PageRequest.of(page, pageLimit, Sort.by(Sort.Order.desc("postTime")));
        Page<Post> postList = postRepository.findAll(pageable);
        Page<PostListResponse> postPageList = postList.map(
                postPage -> new PostListResponse().fromEntity(postPage)
        );
        return postPageList;
    }

}
