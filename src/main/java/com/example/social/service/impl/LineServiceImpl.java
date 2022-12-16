package com.example.social.service.impl;

import com.example.social.dto.request.LineCreateOrUpdateRequest;
import com.example.social.dto.request.LineSearchRequest;
import com.example.social.dto.response.LineResponse;
import com.example.social.entity.LineEntity;
import com.example.social.mapper.LineMapper;
import com.example.social.repository.LineRepository;
import com.example.social.repository.LineRepositoryCustom;
import com.example.social.service.LineService;
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
public class LineServiceImpl implements LineService {

    private final LineRepository lineRepository;

    private final LineRepositoryCustom lineRepositoryCustom;

    private final LineMapper lineMapper;

    @Override
    public LineResponse create(LineCreateOrUpdateRequest request) {
        String ownerId = ensureUserIdLogin();
        LineEntity lineEntity = new LineEntity();
        lineEntity.setId(IdUtils.nextId());
        lineEntity.setChannelToken(request.getChannelToken());
        lineEntity.setChannelName(request.getChannelName());
        lineEntity.setOwnerId(ownerId);
        lineEntity.setDeleted(Boolean.FALSE);
        lineEntity.setCreatedAt(LocalDate.now());
        this.lineRepository.save(lineEntity);
        return this.lineMapper.toDomain(lineEntity);
    }

    @Override
    public LineResponse update(String id, LineCreateOrUpdateRequest request) {
        LineEntity lineEntity = ensureLineEntity(id);
        lineEntity.setChannelToken(request.getChannelToken());
        lineEntity.setChannelName(request.getChannelName());
        lineEntity.setModifiedAt(LocalDate.now());
        this.lineRepository.save(lineEntity);
        return this.lineMapper.toDomain(lineEntity);
    }

    @Override
    public LineResponse delete(String id) {
        LineEntity lineEntity = ensureLineEntity(id);
        lineEntity.setDeleted(Boolean.TRUE);
        this.lineRepository.save(lineEntity);
        return this.lineMapper.toDomain(lineEntity);
    }

    @Override
    public LineResponse findById(String id) {
        return this.lineMapper.toDomain(ensureLineEntity(id));
    }

    @Override
    public PageDTO<LineResponse> search(LineSearchRequest request) {
        List<LineEntity> lineEntities = this.lineRepositoryCustom.search(request, ensureUserIdLogin());
        Long count = this.lineRepositoryCustom.count(request, ensureUserIdLogin());
        if(count <= 0) {
            return new PageDTO<>();
        }
        return new PageDTO<>(
                this.lineMapper.toDomain(lineEntities),
                request.getPageIndex(),
                request.getPageSize(),
                count
        );
    }

    @Override
    public LineResponse findByOwnerId() {
        return this.lineMapper.toDomain(this.lineRepository.findByOwnerId(ensureUserIdLogin()));
    }

    private String ensureUserIdLogin() {
        return SecurityUtils.getCurrentUserLoginId().orElseThrow(
                () -> new ResponseException(AuthenticationError.AUTHENTICATION_ERROR)
        );
    }

    private LineEntity ensureLineEntity(String id) {
        return this.lineRepository.findById(id).orElseThrow(
                () -> new ResponseException(BadRequestError.LINE_NOT_FOUND));
    }
}
