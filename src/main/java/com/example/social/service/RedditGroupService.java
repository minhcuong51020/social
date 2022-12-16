package com.example.social.service;

import com.example.social.dto.request.RedditGroupCreateRequest;
import com.example.social.dto.request.RedditGroupSearchRequest;
import com.example.social.dto.request.RedditGroupUpdateRequest;
import com.example.social.dto.response.RedditGroupResponse;
import com.hmc.common.dto.PageDTO;

import java.util.List;

public interface RedditGroupService {

    RedditGroupResponse create(RedditGroupCreateRequest request);

    RedditGroupResponse update(String id, RedditGroupUpdateRequest request);

    RedditGroupResponse findById(String id);

    RedditGroupResponse delete(String id);

    PageDTO<RedditGroupResponse> search(RedditGroupSearchRequest request);

    PageDTO<RedditGroupResponse> searchAutoComplete(RedditGroupSearchRequest request);

}
