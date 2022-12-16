package com.example.social.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "twitter")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TwitterEntity implements Serializable {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "consummerKey", nullable = false)
    private String consumerKey;

    @Column(name = "consumerSecret", nullable = false)
    private String consumerSecret;

    @Column(name = "oauthToken", nullable = false)
    private String oauthToken;

    @Column(name = "oauthTokenSecret", nullable = false)
    private String oauthTokenSecret;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "modified_at")
    private LocalDate modifiedAt;

    @Column(name = "deleted")
    private Boolean deleted;

    @Column(name = "owner_id", nullable = false)
    private String ownerId;

}
