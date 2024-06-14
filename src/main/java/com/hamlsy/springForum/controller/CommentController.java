package com.hamlsy.springForum.controller;

import com.hamlsy.springForum.dto.request.comment.CommentWriteRequest;
import com.hamlsy.springForum.dto.response.CommentWriteResponse;
import com.hamlsy.springForum.dto.response.post.PostResponse;
import com.hamlsy.springForum.service.CommentService;
import com.hamlsy.springForum.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post/{id}/comment")
public class CommentController {
    private final CommentService commentService;
    private final PostService postService;

    @PostMapping("/write")
    public String commentWrite(@Valid @ModelAttribute("commentWriteRequest") CommentWriteRequest request,
                               BindingResult bindingResult,
                               @PathVariable("id") Long postId,
                               Principal principal, Model model){
        if(bindingResult.hasErrors()){
            log.info("댓글 등록 실패");
            PostResponse response = postService.findPost(postId);
            model.addAttribute("post", response);
            return "post_detail";
        }
        commentService.writeComment(request, postId, principal);
        
        return String.format("redirect:/post/%s", postId);
    }

    @GetMapping("/delete/{commentId}")
    public String commentDelete(@PathVariable("id") Long postId, @PathVariable("commentId") Long commentId){
        commentService.deleteComment(commentId);
        return String.format("redirect:/post/%s", postId);
    }

    @PostMapping("/modify/{commentId}")
    public String commentModify(@PathVariable("id") Long postId, @PathVariable("commentId") Long commentId, @Valid CommentWriteRequest request){
        commentService.modifyComment(commentId, request);
        return String.format("redirect:/post/%s", postId);
    }
}
