package com.example.homecloud.repository;

import com.example.homecloud.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
    File findById(long id);
    boolean existsById(long id);
}
