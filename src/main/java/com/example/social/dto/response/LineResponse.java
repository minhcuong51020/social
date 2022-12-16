package com.example.social.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineResponse implements Serializable {

    private String id;

    private String channelToken;

    private String channelName;

    private LocalDate createdAt;

    private LocalDate modifiedAt;

}
