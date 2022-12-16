package com.example.social.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RedditUpdateRequest implements Serializable {

    private String username;

    private String password;

    private String clientId;

    private String clientSecret;

    private String nameDisplay;

}
