package com.example.social.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RedditGroupCreateRequest implements Serializable {

    private String name;

    private String nameUrl;

    private String description;

}
