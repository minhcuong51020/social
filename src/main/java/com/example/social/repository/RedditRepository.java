package com.example.social.repository;

import com.example.social.entity.RedditEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RedditRepository extends JpaRepository<RedditEntity, String> {

    @Query("select r from RedditEntity r where r.deleted = false and r.ownerId = :ownerId")
    RedditEntity findByOwnerId(@Param("ownerId") String ownerId);

}
