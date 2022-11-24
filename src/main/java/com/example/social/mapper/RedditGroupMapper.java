package com.example.social.mapper;

import com.example.social.dto.response.RedditGroupResponse;
import com.example.social.entity.RedditGroupEntity;
import com.hmc.common.EntityMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface RedditGroupMapper extends EntityMapper<RedditGroupResponse, RedditGroupEntity> {
}
