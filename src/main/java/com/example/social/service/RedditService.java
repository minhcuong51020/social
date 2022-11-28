package com.example.social.service;

import com.example.social.dto.request.RedditCreateRequest;
import com.example.social.dto.request.RedditGroupSearchRequest;
import com.example.social.dto.request.RedditSearchRequest;
import com.example.social.dto.request.RedditUpdateRequest;
import com.example.social.dto.response.RedditGroupResponse;
import com.example.social.dto.response.RedditResponse;
import com.hmc.common.dto.PageDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RedditService {

    RedditResponse create(RedditCreateRequest request);

    RedditResponse update(String id, RedditUpdateRequest request);

    RedditResponse findById(String id);

    List<RedditResponse> findAll();

    RedditResponse delete(String id);

    PageDTO<RedditResponse> search(RedditSearchRequest request);

    RedditResponse findByOwnerId();

}
