package com.sanger.springular.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.sanger.springular.utils.upload.StorageException;
import com.sanger.springular.utils.upload.StorageService;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.core.io.Resource;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class FilesController {

    private final StorageService storageService;

    @GetMapping(value = "/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename, HttpServletRequest request) {
        Resource file = storageService.loadAsResource(filename);

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(file.getFile().getAbsolutePath());
        } catch (IOException ex) {
            throw new StorageException("Error on the storage");
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).body(file);
    }
}
