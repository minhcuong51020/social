package com.example.social.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineCreateOrUpdateRequest implements Serializable {

    private String channelToken;

    private String channelName;

}
