package com.example.social.service;

import com.example.social.dto.request.TwitterCreateOrUpdateRequest;
import com.example.social.dto.request.TwitterSearchRequest;
import com.example.social.dto.response.TwitterResponse;
import com.hmc.common.dto.PageDTO;

public interface TwitterService {

    TwitterResponse create(TwitterCreateOrUpdateRequest request);

    TwitterResponse update(String id, TwitterCreateOrUpdateRequest request);

    TwitterResponse delete(String id);

    TwitterResponse detail(String id);

    PageDTO<TwitterResponse> search(TwitterSearchRequest request);

}
