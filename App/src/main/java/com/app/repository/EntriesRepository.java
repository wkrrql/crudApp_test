package com.app.repository;

import com.app.domain.EntriesDB;
import com.app.dto.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EntriesRepository extends JpaRepository<EntriesDB, Long>{

    @Modifying
    @Query("UPDATE EntriesDB entry SET entry.status = :status ")
    void updateStatusToDelete(@Param("status") Status status);
}
