package com.example.imageuploader.repo;

import com.example.imageuploader.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IImageRepo extends JpaRepository<Image,Long> {
}
