package com.istad.fileupload.controller;

import com.istad.fileupload.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileUploadController {
    @Autowired
    private FileService fileService;

    @Value("src/main/resources/images")
    private String path;
    @PostMapping("/upload") 
    public ResponseEntity<com.istad.fileupload.Response.FileResponse> fileUpload(@RequestParam("image") MultipartFile image){
        String filename = null;
        try {
            filename = this.fileService.uploadImage(path, image);

        } catch (IOException e) {
            return new ResponseEntity<>(new com.istad.fileupload.Response.FileResponse(null, "images can not upload !!!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new com.istad.fileupload.Response.FileResponse(filename, "images is successfully upload to server!!"), HttpStatus.OK);
    }
}
