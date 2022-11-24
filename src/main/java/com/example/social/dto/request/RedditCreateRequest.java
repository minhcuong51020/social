package com.example.social.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RedditCreateRequest implements Serializable {

    private String username;

    private String password;

    private String clientId;

    private String displayName;

    private String clientSecret;

}
