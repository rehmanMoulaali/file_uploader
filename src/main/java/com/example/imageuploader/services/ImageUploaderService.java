package com.example.imageuploader.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.example.imageuploader.Dtos.ImageOutPutDto;
import com.example.imageuploader.models.Image;
import com.example.imageuploader.repo.IImageRepo;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class ImageUploaderService {
    @Autowired
    private AmazonS3 client;
    @Autowired
    private IImageRepo imageRepo;

    @Value("${BUCKET_NAME}")
    private String bucketName;

    @SneakyThrows
    public String ImageUploader(MultipartFile image){
        String orgName = image.getOriginalFilename();

        ObjectMetadata objectMetadata=new ObjectMetadata();
        objectMetadata.setContentLength(image.getSize());
        String toSave = UUID.randomUUID().toString() + orgName.substring(orgName.lastIndexOf("."));
        try {
            PutObjectResult putObjectResult = client.putObject(bucketName,toSave,image.getInputStream(),objectMetadata);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        imageRepo.save(new Image(Long.valueOf(1l),orgName,toSave));
        return toSave;
    }

    private String getUnSignedUrl(String imageId){
        Date expire=new Date();
        long time = expire.getTime();
        time=time+3*60*60*1000;
        expire.setTime(time);
        return client.generatePresignedUrl(bucketName,imageId, expire).toString();
    }

    public List<ImageOutPutDto> getAllImages(){
        List<ImageOutPutDto> imageOutPutDtos=new ArrayList<>();
         List<Image> images=imageRepo.findAll();

         for(var image : images){
            imageOutPutDtos.add(new ImageOutPutDto(image.getId(),image.getOriginalName(),getUnSignedUrl(image.getSavedName())));
         }
         return imageOutPutDtos;
    }
}
