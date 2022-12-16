package com.example.social.service.impl;

import com.example.social.dto.request.RedditGroupCreateRequest;
import com.example.social.dto.request.RedditGroupSearchRequest;
import com.example.social.dto.request.RedditGroupUpdateRequest;
import com.example.social.dto.response.RedditGroupResponse;
import com.example.social.entity.RedditGroupEntity;
import com.example.social.mapper.RedditGroupMapper;
import com.example.social.repository.RedditGroupRepository;
import com.example.social.repository.RedditGroupRepositoryCustom;
import com.example.social.service.RedditGroupService;
import com.example.social.support.BadRequestError;
import com.hmc.common.dto.PageDTO;
import com.hmc.common.enums.error.AuthenticationError;
import com.hmc.common.exception.ResponseException;
import com.hmc.common.util.IdUtils;
import com.hmc.config.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class RedditGroupServiceImpl implements RedditGroupService {

    private final RedditGroupRepository redditGroupRepository;

    private final RedditGroupMapper redditGroupMapper;

    private final RedditGroupRepositoryCustom redditGroupRepositoryCustom;

    public RedditGroupServiceImpl(RedditGroupRepository redditGroupRepository,
                                  RedditGroupMapper redditGroupMapper,
                                  RedditGroupRepositoryCustom redditGroupRepositoryCustom) {
        this.redditGroupRepository = redditGroupRepository;
        this.redditGroupMapper = redditGroupMapper;
        this.redditGroupRepositoryCustom = redditGroupRepositoryCustom;
    }


    @Override
    public RedditGroupResponse create(RedditGroupCreateRequest request) {
        String ownerId = ensureUserIdLogin();
        RedditGroupEntity redditGroupEntity = new RedditGroupEntity();
        redditGroupEntity.setId(IdUtils.nextId());
        redditGroupEntity.setName(request.getName());
        redditGroupEntity.setNameUrl(request.getNameUrl());
        redditGroupEntity.setDescription(request.getDescription());
        redditGroupEntity.setOwnerId(ownerId);
        redditGroupEntity.setDeleted(Boolean.FALSE);
        redditGroupEntity.setCreatedAt(LocalDate.now());
        this.redditGroupRepository.save(redditGroupEntity);
        return this.redditGroupMapper.toDomain(redditGroupEntity);
    }

    @Override
    public RedditGroupResponse update(String id, RedditGroupUpdateRequest request) {
        RedditGroupEntity redditGroupEntity = ensureRedditGroup(id);
        redditGroupEntity.setName(request.getName());
        redditGroupEntity.setNameUrl(request.getNameUrl());
        redditGroupEntity.setDescription(request.getDescription());
        redditGroupEntity.setModifiedAt(LocalDate.now());
        this.redditGroupRepository.save(redditGroupEntity);
        return this.redditGroupMapper.toDomain(redditGroupEntity);
    }

    @Override
    public RedditGroupResponse findById(String id) {
        RedditGroupEntity redditGroupEntity = ensureRedditGroup(id);
        return this.redditGroupMapper.toDomain(redditGroupEntity);
    }

    @Override
    public RedditGroupResponse delete(String id) {
        RedditGroupEntity redditGroupEntity = ensureRedditGroup(id);
        redditGroupEntity.setDeleted(Boolean.TRUE);
        this.redditGroupRepository.save(redditGroupEntity);
        return this.redditGroupMapper.toDomain(redditGroupEntity);
    }

    @Override
    public PageDTO<RedditGroupResponse> search(RedditGroupSearchRequest request) {
        List<RedditGroupEntity> redditGroupEntities = this.redditGroupRepositoryCustom.search(request);
        List<RedditGroupResponse> redditGroupResponses = this.redditGroupMapper.toDomain(redditGroupEntities);
        Long count = this.redditGroupRepositoryCustom.count(request);
        if(count <= 0) {
            return new PageDTO<>();
        }
        return new PageDTO<>(
                redditGroupResponses,
                request.getPageIndex(),
                request.getPageSize(),
                count
        );
    }

    @Override
    public PageDTO<RedditGroupResponse> searchAutoComplete(RedditGroupSearchRequest request) {
        Long count = this.redditGroupRepository.countRedditGroupEntity();
        if(count <= 0) {
            return new PageDTO<>();
        }
        request.setPageSize(Integer.parseInt(count.toString()));
        List<RedditGroupEntity> redditGroupEntities = this.redditGroupRepositoryCustom.search(request);
        List<RedditGroupResponse> redditGroupResponses = this.redditGroupMapper.toDomain(redditGroupEntities);
        return new PageDTO<>(
                redditGroupResponses,
                request.getPageIndex(),
                request.getPageIndex(),
                count
        );
    }

    private RedditGroupEntity ensureRedditGroup(String id) {
        return this.redditGroupRepository.findById(id).orElseThrow(
                () -> new ResponseException(BadRequestError.REDDIT_GROUP_NOT_FOUND)
        );
    }

    private String ensureUserIdLogin() {
        return SecurityUtils.getCurrentUserLoginId().orElseThrow(
                () -> new ResponseException(AuthenticationError.AUTHENTICATION_ERROR)
        );
    }

}
