package com.dealflowbus.databasemainreader.controller;

import com.dealflowbus.databasemainreader.models.UploadFileResponse;
import com.dealflowbus.databasemainreader.services.DBFileService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api")
public class FilesController {

    private final DBFileService fileService;

    public FilesController(DBFileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/leads/{id}/files")
    @PreAuthorize("hasAuthority('create_lead')")
    public UploadFileResponse uploadFile(@RequestParam MultipartFile file, @PathVariable int id) {

        return fileService.storeFile(file, id);
    }

    @SuppressWarnings("rawtypes")
    @GetMapping("/files/{fileId}")
    @PreAuthorize("hasAuthority('read_lead')")
    public ResponseEntity downloadFile(@PathVariable String fileId) {

        return fileService.getFile(fileId);
    }

    @DeleteMapping("/files/{fileId}")
    @PreAuthorize("hasAuthority('delete_lead')")
    public String deleteFile(@PathVariable String fileId) {

        return fileService.deleteFile(fileId);
    }
}





