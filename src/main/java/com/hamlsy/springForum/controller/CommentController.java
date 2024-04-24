package com.hamlsy.springForum.controller;

import com.hamlsy.springForum.dto.request.comment.CommentWriteRequest;
import com.hamlsy.springForum.dto.response.CommentWriteResponse;
import com.hamlsy.springForum.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post/{id}/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/write")
    public String commentWrite(@Valid CommentWriteRequest request,
                               @PathVariable("id") Long postId,
                               Principal principal){
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
