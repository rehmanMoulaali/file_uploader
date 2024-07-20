package com.example.imageuploader.controller;

import com.example.imageuploader.Dtos.ImageOutPutDto;
import com.example.imageuploader.services.ImageUploaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class ImageController {
    @Autowired
    private ImageUploaderService imageUploaderService;
    @PostMapping("/file")
    public String upload(@RequestParam MultipartFile file){
        return imageUploaderService.ImageUploader(file);
    }

    @GetMapping("/")
    public List<ImageOutPutDto> getAllImages(){
        return imageUploaderService.getAllImages();
    }
}
