package com.example.social.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RedditGroupResponse implements Serializable {

    private String id;

    private String name;

    private String nameUrl;

    private String ownerId;

    private String description;

    private LocalDate modifiedAt;

    private LocalDate createdAt;

}
