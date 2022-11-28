package com.example.social.resources;

import com.example.social.dto.request.*;
import com.example.social.dto.response.RedditGroupResponse;
import com.example.social.dto.response.RedditResponse;
import com.hmc.common.dto.response.PagingResponse;
import com.hmc.common.dto.response.Response;
import com.hmc.common.validator.ValidatePaging;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/social")
public interface RedditResource {

    @PostMapping("/api/reddit")
    Response<RedditResponse> createReddit(@RequestBody RedditCreateRequest request);

    @GetMapping("/api/reddit/user-owner")
    Response<RedditResponse> findRedditByOwner();

    @PostMapping("/api/reddit/{id}/update")
    Response<RedditResponse> updateReddit(@PathVariable("id") String id,
                                          @RequestBody RedditUpdateRequest request);

    @GetMapping("/api/reddit")
    PagingResponse<RedditResponse> searchReddit(@ValidatePaging(allowedSorts = {"name"})
                                                          RedditSearchRequest request);

    @PostMapping("/api/reddit/{id}/delete")
    Response<RedditResponse> deleteReddit(@PathVariable("id") String id);

    @PostMapping("/api/reddit-group")
    Response<RedditGroupResponse> createRedditGroup(@RequestBody RedditGroupCreateRequest request);

    @PostMapping("/api/reddit-group/{id}/update")
    Response<RedditGroupResponse> updateRedditGroup(@PathVariable("id") String id,
                                                    @RequestBody RedditGroupUpdateRequest request);

    @GetMapping("/api/reddit-group/{id}/detail")
    Response<RedditGroupResponse> updateRedditGroup(@PathVariable("id") String id);

    @GetMapping("/api/reddit-group")
    PagingResponse<RedditGroupResponse> searchRedditGroup(@ValidatePaging(allowedSorts = {"name"})
                                                          RedditGroupSearchRequest request);

    @GetMapping("/api/reddit-group/auto-complete")
    PagingResponse<RedditGroupResponse> searchRedditGroupAuto(@ValidatePaging(allowedSorts = {"name"})
                                                          RedditGroupSearchRequest request);

}
