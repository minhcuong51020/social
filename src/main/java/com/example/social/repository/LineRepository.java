package com.example.social.repository;

import com.example.social.entity.LineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LineRepository extends JpaRepository<LineEntity, String> {

    @Query("select l from LineEntity l where l.deleted = false and l.ownerId = :ownerId")
    LineEntity findByOwnerId(@Param("ownerId") String ownerId);

}
