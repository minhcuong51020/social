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
@Table(name = "line")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineEntity implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "channel_token", nullable = false)
    private String channelToken;

    @Column(name = "owner_id", nullable = false)
    private String ownerId;

    @Column(name = "channel_name")
    private String channelName;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "modified_at")
    private LocalDate modifiedAt;

    @Column(name = "deleted")
    private Boolean deleted;

}
