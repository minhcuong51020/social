package com.example.social.repository;

import com.example.social.entity.TwitterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TwitterRepository extends JpaRepository<TwitterEntity, String> {

    @Query("select p from TwitterEntity p where p.deleted = false and p.ownerId = :ownerId")
    TwitterEntity findByOwnerId(@Param("ownerId") String ownerId);

}
