package com.example.social.resources;

import com.example.social.dto.request.*;
import com.example.social.dto.response.RedditGroupResponse;
import com.example.social.dto.response.RedditResponse;
import com.hmc.common.dto.response.PagingResponse;
import com.hmc.common.dto.response.Response;
import com.hmc.common.validator.ValidatePaging;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/social")
public interface RedditResource {

    @PostMapping("/api/reddit")
    @PreAuthorize("hasAnyRole({'ROLE_USER', 'ROLE_ADMIN'})")
    Response<RedditResponse> createReddit(@RequestBody RedditCreateRequest request);

    @GetMapping("/api/reddit/user-owner")
    @PreAuthorize("hasAnyRole({'ROLE_USER', 'ROLE_ADMIN'})")
    Response<RedditResponse> findRedditByOwner();

    @PostMapping("/api/reddit/{id}/update")@PreAuthorize("hasAnyRole({'ROLE_USER', 'ROLE_ADMIN'})")
    Response<RedditResponse> updateReddit(@PathVariable("id") String id,
                                          @RequestBody RedditUpdateRequest request);

    @GetMapping("/api/reddit")
    @PreAuthorize("hasAnyRole({'ROLE_USER', 'ROLE_ADMIN'})")
    PagingResponse<RedditResponse> searchReddit(@ValidatePaging(allowedSorts = {"name"})
                                                          RedditSearchRequest request);

    @PostMapping("/api/reddit/{id}/delete")
    @PreAuthorize("hasAnyRole({'ROLE_USER', 'ROLE_ADMIN'})")
    Response<RedditResponse> deleteReddit(@PathVariable("id") String id);

    @PostMapping("/api/reddit-group")
    @PreAuthorize("hasAnyRole({'ROLE_USER', 'ROLE_ADMIN'})")
    Response<RedditGroupResponse> createRedditGroup(@RequestBody RedditGroupCreateRequest request);

    @PostMapping("/api/reddit-group/{id}/update")
    @PreAuthorize("hasAnyRole({'ROLE_USER', 'ROLE_ADMIN'})")
    Response<RedditGroupResponse> updateRedditGroup(@PathVariable("id") String id,
                                                    @RequestBody RedditGroupUpdateRequest request);

    @GetMapping("/api/reddit-group/{id}/detail")
    @PreAuthorize("hasAnyRole({'ROLE_USER', 'ROLE_ADMIN'})")
    Response<RedditGroupResponse> detailRedditGroup(@PathVariable("id") String id);

    @PostMapping("/api/reddit-group/{id}/delete")
    @PreAuthorize("hasAnyRole({'ROLE_USER', 'ROLE_ADMIN'})")
    Response<RedditGroupResponse> deleteRedditGroup(@PathVariable("id") String id);

    @GetMapping("/api/reddit-group")
    @PreAuthorize("hasAnyRole({'ROLE_USER', 'ROLE_ADMIN'})")
    PagingResponse<RedditGroupResponse> searchRedditGroup(@ValidatePaging(allowedSorts = {"name"})
                                                          RedditGroupSearchRequest request);

    @GetMapping("/api/reddit-group/auto-complete")
    @PreAuthorize("hasAnyRole({'ROLE_USER', 'ROLE_ADMIN'})")
    PagingResponse<RedditGroupResponse> searchRedditGroupAuto(@ValidatePaging(allowedSorts = {"name"})
                                                          RedditGroupSearchRequest request);

    @GetMapping("/api/reddit/{id}/detail")
    @PreAuthorize("hasAnyRole({'ROLE_USER', 'ROLE_ADMIN'})")
    Response<RedditResponse> redditDetail(@PathVariable("id") String id);
}
