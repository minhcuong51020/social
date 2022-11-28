package com.example.social.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RedditResponse implements Serializable {

    private String id;

    private String nameDisplay;

    private String clientId;

    private String clientSecret;

    private String username;

    private String password;

    private String ownerId;

    private LocalDate modifiedAt;

    private LocalDate createdAt;

}
