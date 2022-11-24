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
@Table(name = "reddit_group")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RedditGroupEntity implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "name_url", nullable = false)
    private String nameUrl;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "owner_id", nullable = false)
    private String ownerId;

    @Column(name = "deleted")
    private Boolean deleted;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "modified_at")
    private LocalDate modifiedAt;

}
