package com.example.social.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TwitterCreateOrUpdateRequest implements Serializable {

    private String name;

    private String consumerKey;

    private String consumerSecret;

    private String oauthToken;

    private String oauthTokenSecret;

}
