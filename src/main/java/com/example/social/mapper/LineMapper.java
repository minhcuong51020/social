package com.example.social.mapper;

import com.example.social.dto.response.LineResponse;
import com.example.social.entity.LineEntity;
import com.hmc.common.EntityMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface LineMapper extends EntityMapper<LineResponse, LineEntity> {
}
