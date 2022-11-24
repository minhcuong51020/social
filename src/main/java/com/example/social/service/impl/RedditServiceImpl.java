package com.example.social.service.impl;

import com.example.social.dto.request.RedditCreateRequest;
import com.example.social.dto.request.RedditGroupSearchRequest;
import com.example.social.dto.request.RedditUpdateRequest;
import com.example.social.dto.response.RedditGroupResponse;
import com.example.social.dto.response.RedditResponse;
import com.example.social.entity.RedditEntity;
import com.example.social.entity.RedditGroupEntity;
import com.example.social.mapper.RedditMapper;
import com.example.social.repository.RedditRepository;
import com.example.social.service.RedditService;
import com.example.social.support.BadRequestError;
import com.hmc.common.dto.PageDTO;
import com.hmc.common.enums.error.AuthenticationError;
import com.hmc.common.exception.ResponseException;
import com.hmc.common.util.IdUtils;
import com.hmc.config.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class RedditServiceImpl implements RedditService {

    private final RedditRepository redditRepository;

    private final RedditMapper redditMapper;

    public RedditServiceImpl(RedditRepository redditRepository, RedditMapper redditMapper) {
        this.redditRepository = redditRepository;
        this.redditMapper = redditMapper;
    }

    @Override
    public RedditResponse create(RedditCreateRequest request) {
        String ownerId = ensureUserIdLogin();
        if(Objects.nonNull(this.redditRepository.findByOwnerId(ownerId))) {
            throw new ResponseException(BadRequestError.REDDIT_USER_ONLY_ONE);
        }
        RedditEntity redditEntity = new RedditEntity();
        redditEntity.setId(IdUtils.nextId());
        redditEntity.setUsername(request.getUsername());
        redditEntity.setPassword(request.getPassword());
        redditEntity.setClientId(redditEntity.getClientId());
        redditEntity.setClientSecret(redditEntity.getClientSecret());
        redditEntity.setNameDisplay(redditEntity.getNameDisplay());
        redditEntity.setOwnerId(ownerId);
        redditEntity.setDeleted(Boolean.FALSE);
        this.redditRepository.save(redditEntity);
        return this.redditMapper.toDomain(redditEntity);
    }

    @Override
    public RedditResponse update(String id, RedditUpdateRequest request) {
        RedditEntity redditEntity = this.ensureReddit(id);
        redditEntity.setClientId(request.getClientId());
        redditEntity.setClientSecret(request.getClientSecret());
        redditEntity.setUsername(request.getUsername());
        redditEntity.setPassword(request.getPassword());
        this.redditRepository.save(redditEntity);
        return this.redditMapper.toDomain(redditEntity);
    }

    @Override
    public RedditResponse findById(String id) {
        String ownerId = ensureUserIdLogin();
        RedditEntity redditEntity = ensureReddit(id);
        if(!ownerId.equals(redditEntity.getOwnerId())) {
            throw new ResponseException(BadRequestError.REDDIT_NOT_FOUND);
        }
        return this.redditMapper.toDomain(redditEntity);
    }

    @Override
    public List<RedditResponse> findAll() {
        String ownerId = ensureUserIdLogin();
        RedditEntity redditEntity = this.redditRepository.findByOwnerId(ownerId);
        return List.of(this.redditMapper.toDomain(redditEntity));
    }

    @Override
    public RedditResponse delete(String id) {
        RedditEntity redditEntity = this.ensureReddit(id);
        redditEntity.setDeleted(Boolean.FALSE);
        this.redditRepository.save(redditEntity);
        return this.redditMapper.toDomain(redditEntity);
    }

    private RedditEntity ensureReddit(String id) {
        return this.redditRepository.findById(id).orElseThrow(
                () -> new ResponseException(BadRequestError.REDDIT_NOT_FOUND)
        );
    }

    private String ensureUserIdLogin() {
        return SecurityUtils.getCurrentUserLoginId().orElseThrow(
                () -> new ResponseException(AuthenticationError.AUTHENTICATION_ERROR)
        );
    }
}
