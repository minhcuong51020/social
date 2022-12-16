package com.example.social.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TwitterResponse implements Serializable {

    private String id;

    private String name;

    private String consumerKey;

    private String consumerSecret;

    private String oauthToken;

    private String oauthTokenSecret;

    private String ownerId;

    private LocalDate createdAt;

    private LocalDate modifiedAt;

}
