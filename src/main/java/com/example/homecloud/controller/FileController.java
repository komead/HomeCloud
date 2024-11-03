package com.example.homecloud.controller;

import com.example.homecloud.entity.File;
import com.example.homecloud.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
@AllArgsConstructor
public class FileController {
    private FileService fileService;

    @GetMapping("/info/{id}")
    public File getInfo(@PathVariable (value = "id") long id) {
        return fileService.getFile(id);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> saveFile(
            @RequestParam("file")
            MultipartFile file) {
        try {
            fileService.saveFile(file);
            return ResponseEntity.ok("File uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("File upload failed: " + e.getMessage());
        }
    }
}
