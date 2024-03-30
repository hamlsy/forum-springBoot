package com.hamlsy.springForum.controller;

import com.hamlsy.springForum.dto.request.comment.CommentWriteRequest;
import com.hamlsy.springForum.dto.response.CommentWriteResponse;
import com.hamlsy.springForum.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post/{id}/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/write")
    public String commentWrite(CommentWriteRequest request,
                               @PathVariable("id") Long postId,
                               Principal principal, Model model){
        CommentWriteResponse response = commentService.writeComment(request, postId, principal);
        return String.format("redirect:/post/%s", postId);
    }

    //수정 필요
    @GetMapping("/delete/{commentId}")
    public String commentDelete(@PathVariable("id") Long postId, @PathVariable Long commentId){
        commentService.deleteComment(commentId);
        return String.format("redirect:/post/%s", postId);
    }
}
