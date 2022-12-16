package com.example.social.service.impl;

import com.example.social.dto.request.TwitterCreateOrUpdateRequest;
import com.example.social.dto.request.TwitterSearchRequest;
import com.example.social.dto.response.TwitterResponse;
import com.example.social.entity.TwitterEntity;
import com.example.social.mapper.TwitterMapper;
import com.example.social.repository.TwitterRepository;
import com.example.social.repository.TwitterRepositoryCustom;
import com.example.social.service.TwitterService;
import com.example.social.support.BadRequestError;
import com.hmc.common.dto.PageDTO;
import com.hmc.common.enums.error.AuthenticationError;
import com.hmc.common.exception.ResponseException;
import com.hmc.common.util.IdUtils;
import com.hmc.config.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TwitterServiceImpl implements TwitterService {

    private final TwitterRepository twitterRepository;

    private final TwitterRepositoryCustom twitterRepositoryCustom;

    private final TwitterMapper twitterMapper;

    @Override
    public TwitterResponse create(TwitterCreateOrUpdateRequest request) {
        String ownerId = ensureUserIdLogin();
        TwitterEntity twitterEntity = new TwitterEntity();
        twitterEntity.setId(IdUtils.nextId());
        twitterEntity.setName(request.getName());
        twitterEntity.setConsumerKey(request.getConsumerKey());
        twitterEntity.setConsumerSecret(request.getConsumerSecret());
        twitterEntity.setOauthToken(request.getOauthToken());
        twitterEntity.setOauthTokenSecret(request.getOauthTokenSecret());
        twitterEntity.setCreatedAt(LocalDate.now());
        twitterEntity.setOwnerId(ownerId);
        twitterEntity.setDeleted(Boolean.FALSE);
        twitterRepository.save(twitterEntity);
        return twitterMapper.toDomain(twitterEntity);
    }

    @Override
    public TwitterResponse update(String id, TwitterCreateOrUpdateRequest request) {
        TwitterEntity twitterEntity = ensureTwitterEntity(id);
        twitterEntity.setName(request.getName());
        twitterEntity.setConsumerSecret(request.getConsumerSecret());
        twitterEntity.setConsumerKey(request.getConsumerKey());
        twitterEntity.setOauthToken(request.getOauthToken());
        twitterEntity.setOauthTokenSecret(request.getOauthTokenSecret());
        twitterEntity.setModifiedAt(LocalDate.now());
        twitterRepository.save(twitterEntity);
        return twitterMapper.toDomain(twitterEntity);
    }

    @Override
    public TwitterResponse delete(String id) {
        TwitterEntity twitterEntity = ensureTwitterEntity(id);
        twitterEntity.setDeleted(Boolean.TRUE);
        twitterRepository.save(twitterEntity);
        return twitterMapper.toDomain(twitterEntity);
    }

    @Override
    public TwitterResponse detail(String id) {
        TwitterEntity twitterEntity = ensureTwitterEntity(id);
        return twitterMapper.toDomain(twitterEntity);
    }

    @Override
    public PageDTO<TwitterResponse> search(TwitterSearchRequest request) {
        List<TwitterEntity> twitterEntities = this.twitterRepositoryCustom.search(request, ensureUserIdLogin());
        Long count = this.twitterRepositoryCustom.count(request, ensureUserIdLogin());
        if(count <= 0) {
            return new PageDTO<>();
        }
        return new PageDTO<>(
                this.twitterMapper.toDomain(twitterEntities),
                request.getPageIndex(),
                request.getPageSize(),
                count
        );
    }

    private String ensureUserIdLogin() {
        return SecurityUtils.getCurrentUserLoginId().orElseThrow(
                () -> new ResponseException(AuthenticationError.AUTHENTICATION_ERROR)
        );
    }

    private TwitterEntity ensureTwitterEntity(String id) {
        return twitterRepository.findById(id).orElseThrow(
                () -> new ResponseException(BadRequestError.TUMBLR_NOT_FOUND)
        );
    }
}
