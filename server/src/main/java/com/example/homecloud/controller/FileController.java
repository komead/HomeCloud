package com.example.homecloud.controller;

import com.example.homecloud.entity.File;
import com.example.homecloud.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

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

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteFile(@PathVariable Long id) {
        try {
            fileService.deleteFile(id);
            return ResponseEntity.ok("File deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete file: " + e.getMessage());
        }
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable long id) {
        try {
            Resource fileResource = fileService.loadFile(id);

            String encodedFileName = URLEncoder.encode(fileResource.getFilename(), StandardCharsets.UTF_8.toString())
                    .replace("+", "%20");

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=UTF-8''" + encodedFileName)
                    .body(fileResource);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
