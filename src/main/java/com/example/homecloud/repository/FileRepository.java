package com.example.homecloud.repository;

import com.example.homecloud.entity.File;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository {
    File findById(int id);
    boolean existsById(int id);
}
