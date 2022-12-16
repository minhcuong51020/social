package com.example.social.mapper;

import com.example.social.dto.response.TwitterResponse;
import com.example.social.entity.TwitterEntity;
import com.hmc.common.EntityMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface TwitterMapper extends EntityMapper<TwitterResponse, TwitterEntity> {
}
