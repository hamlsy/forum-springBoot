package com.hamlsy.forumApi.api;

import com.hamlsy.forumApi.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentApiController {
    private final CommentService commentService;
}
