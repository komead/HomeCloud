package com.example.homecloud.service;

import com.example.homecloud.entity.File;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    File getFile(long id);
    void saveFile(MultipartFile file);
    Resource loadFile(long id);
}
