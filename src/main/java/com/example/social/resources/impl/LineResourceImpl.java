package com.example.social.resources.impl;

import com.example.social.dto.request.LineCreateOrUpdateRequest;
import com.example.social.dto.request.LineSearchRequest;
import com.example.social.dto.response.LineResponse;
import com.example.social.resources.LineResource;
import com.example.social.service.LineService;
import com.hmc.common.dto.response.PagingResponse;
import com.hmc.common.dto.response.Response;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LineResourceImpl implements LineResource {

    private final LineService lineService;

    public LineResourceImpl(LineService lineService) {
        this.lineService = lineService;
    }

    @Override
    public Response<LineResponse> create(LineCreateOrUpdateRequest request) {
        return Response.of(lineService.create(request));
    }

    @Override
    public Response<LineResponse> update(String id, LineCreateOrUpdateRequest request) {
        return Response.of(lineService.update(id, request));
    }

    @Override
    public Response<LineResponse> detail(String id) {
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        return Response.of(lineService.findById(id));
    }

    @Override
    public Response<LineResponse> delete(String id) {
        return Response.of(lineService.delete(id));
    }

    @Override
    public PagingResponse<LineResponse> search(LineSearchRequest request) {
        return PagingResponse.of(lineService.search(request));
    }

    @Override
    public Response<LineResponse> findByOwner() {
        return Response.of(lineService.findByOwnerId());
    }
}
