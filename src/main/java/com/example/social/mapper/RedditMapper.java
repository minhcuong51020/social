package com.example.social.mapper;

import com.example.social.dto.response.RedditResponse;
import com.example.social.entity.RedditEntity;
import com.hmc.common.EntityMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface RedditMapper extends EntityMapper<RedditResponse, RedditEntity> {
}
