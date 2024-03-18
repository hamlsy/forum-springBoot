package com.hamlsy.forumApi.controller;

import com.hamlsy.forumApi.dto.request.comment.CommentWriteRequest;
import com.hamlsy.forumApi.dto.response.CommentWriteResponse;
import com.hamlsy.forumApi.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
