package com.rookie.rookiee.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.boot.autoconfigure.web.ServerProperties.Tomcat.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/file")
public class fileController {

    @PostMapping("/add")
    public ResponseEntity<String> postMethodName(@RequestBody MultipartFile image) throws IOException {
        byte[] bytes = image.getBytes();
        String directory = "C:/Users/Khoa/Documents/Rookie/Image/";
        String name = UUID.randomUUID().toString();
        name = name + ".png";

        Path path = Paths.get(directory + name);
        Files.write(path, bytes);

        return ResponseEntity.ok().body(name);
    }

    // @GetMapping("/image")
    // public ResponseEntity<Resource> getMethodName(@RequestBody List<String>
    // urlList) {

    // List<Resource> imgs= new ArrayList<>();

    // for(String s: urlList){
    // imgs.add(Files.readAllBytes(null))
    // }

    // return new String();
    // }

}
