package com.example.social.resources.impl;

import com.example.social.dto.request.*;
import com.example.social.dto.response.RedditGroupResponse;
import com.example.social.dto.response.RedditResponse;
import com.example.social.resources.RedditResource;
import com.example.social.service.RedditGroupService;
import com.example.social.service.RedditService;
import com.hmc.common.dto.response.PagingResponse;
import com.hmc.common.dto.response.Response;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedditResourceImpl implements RedditResource {

    private final RedditService redditService;

    private final RedditGroupService redditGroupService;

    public RedditResourceImpl(RedditService redditService,
                              RedditGroupService redditGroupService) {
        this.redditService = redditService;
        this.redditGroupService = redditGroupService;
    }

    @Override
    public Response<RedditResponse> createReddit(RedditCreateRequest request) {
        return Response.of(this.redditService.create(request));
    }

    @Override
    public Response<RedditResponse> updateReddit(String id, RedditUpdateRequest request) {
        return Response.of(this.redditService.update(id, request));
    }

    @Override
    public Response<RedditGroupResponse> createRedditGroup(RedditGroupCreateRequest request) {
        return Response.of(this.redditGroupService.create(request));
    }

    @Override
    public Response<RedditGroupResponse> updateRedditGroup(String id, RedditGroupUpdateRequest request) {
        return Response.of(this.redditGroupService.update(id, request));
    }

    @Override
    public PagingResponse<RedditGroupResponse> searchRedditGroup(RedditGroupSearchRequest request) {
        return PagingResponse.of(this.redditGroupService.search(request));
    }

    @Override
    public PagingResponse<RedditGroupResponse> searchRedditGroupAuto(RedditGroupSearchRequest request) {
        return PagingResponse.of(this.redditGroupService.searchAutoComplete(request));
    }


}
