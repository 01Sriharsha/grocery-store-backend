package com.grocery.grocerystorebackend.controller;

import com.grocery.grocerystorebackend.entity.Image;
import com.grocery.grocerystorebackend.entity.Product;
import com.grocery.grocerystorebackend.repository.ImageRepository;
import com.grocery.grocerystorebackend.repository.ProductRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/v1")
public class ImageController {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ProductRepository productRepository;


    @PostMapping("/products/{productId}/upload/images")
    public ResponseEntity<?> uploadImage(
            @PathVariable String productId,
            @RequestParam("images") MultipartFile[] images
    ) {
        Product product = productRepository.findById((productId))
                .orElseThrow(() -> new RuntimeException("Product not found"));
        Arrays.stream(images).forEach(image -> {
            try {
                Image imageEntity = new Image();
                imageEntity.setName(image.getOriginalFilename());
                imageEntity.setFile(image.getBytes());
                imageEntity.setProduct(product);
                imageRepository.save(imageEntity);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return new ResponseEntity<>("success", HttpStatus.OK);
    }


    @GetMapping("/download/images/{imageId}")
    public ResponseEntity<?> downloadSingleImage(@PathVariable Integer imageId, HttpServletResponse response) throws IOException {
        Image image = imageRepository.findById(imageId)
                .orElseThrow(() -> new RuntimeException("Image not found"));
        ByteArrayInputStream inputStream = new ByteArrayInputStream(image.getFile());
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(inputStream, response.getOutputStream());
        return new ResponseEntity<>("success", HttpStatus.OK);
    }


    @GetMapping("/products/{productId}/download/images")
    public ResponseEntity<?> downloadSingleImage(@PathVariable String productId) throws IOException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        List<Image> productImages = imageRepository.findByProduct(product);
        List<String> imageUriList = productImages.stream().map(image -> {
            return ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/v1/download/images/")
                    .path(String.valueOf(image.getId()))
                    .toUriString();
        }).toList();
        return new ResponseEntity<>(imageUriList, HttpStatus.OK);
    }


}
