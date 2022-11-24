package com.example.social.repository;

import com.example.social.entity.RedditGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RedditGroupRepository extends JpaRepository<RedditGroupEntity, String> {

    @Query("select count(r) from RedditGroupEntity r where r.deleted = false")
    Long countRedditGroupEntity();

}
