package com.example.homecloud.service.impl;

import com.example.homecloud.entity.File;
import com.example.homecloud.repository.FileRepository;
import com.example.homecloud.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@Service
@AllArgsConstructor
public class FileServiceImpl implements FileService {
    private FileRepository fileRepository;

    private final String uploadDirectory = "uploads";

    @Override
    public File getFile(long id) {
        return fileRepository.findById(id);
    }

    @Override
    public void saveFile(MultipartFile file) {
        Path directory = Paths.get(uploadDirectory);
        if (!Files.exists(directory)) {
            try {
                Files.createDirectories(directory);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        Path filePath = Paths.get(uploadDirectory, file.getOriginalFilename());
        try {
            Files.write(filePath, file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        saveFile(file, filePath.toString());
    }

    private void saveFile(MultipartFile file, String filePath) {
        String fileName = file.getOriginalFilename();
        File metadata = new File();

        if (fileName != null && fileName.contains(".")) {
            String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
            metadata.setExtension(extension);
        } else {
            metadata.setExtension("unknown");
        }

        metadata.setName(fileName);
        metadata.setPath(filePath);
        metadata.setSize(file.getSize());
        metadata.setUploadTime(new Date());

        fileRepository.save(metadata);
    }
}
