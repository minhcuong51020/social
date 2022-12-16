package com.example.social.resources.impl;

import com.example.social.dto.request.TwitterCreateOrUpdateRequest;
import com.example.social.dto.request.TwitterSearchRequest;
import com.example.social.dto.response.TwitterResponse;
import com.example.social.resources.TwitterResource;
import com.example.social.service.TwitterService;
import com.hmc.common.dto.response.PagingResponse;
import com.hmc.common.dto.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TwitterResourceImpl implements TwitterResource {

    private final TwitterService tumblrService;

    @Override
    public Response<TwitterResponse> create(TwitterCreateOrUpdateRequest request) {
        return Response.of(tumblrService.create(request));
    }

    @Override
    public Response<TwitterResponse> update(String id, TwitterCreateOrUpdateRequest request) {
        return Response.of(tumblrService.update(id, request));
    }

    @Override
    public Response<TwitterResponse> delete(String id) {
        return Response.of(tumblrService.delete(id));
    }

    @Override
    public Response<TwitterResponse> detail(String id) {
        return Response.of(tumblrService.detail(id));
    }

    @Override
    public PagingResponse<TwitterResponse> search(TwitterSearchRequest request) {
        return PagingResponse.of(tumblrService.search(request));
    }
}
