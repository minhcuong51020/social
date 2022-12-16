package com.example.social.service;

import com.example.social.dto.request.LineCreateOrUpdateRequest;
import com.example.social.dto.request.LineSearchRequest;
import com.example.social.dto.response.LineResponse;
import com.hmc.common.dto.PageDTO;

public interface LineService {

    LineResponse create(LineCreateOrUpdateRequest request);

    LineResponse update(String id, LineCreateOrUpdateRequest request);

    LineResponse delete(String id);

    LineResponse findById(String id);

    PageDTO<LineResponse> search(LineSearchRequest request);

    LineResponse findByOwnerId();

}
