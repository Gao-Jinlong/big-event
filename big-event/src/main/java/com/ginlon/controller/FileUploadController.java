package com.ginlon.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ginlon.pojo.Result;

@RestController
@RequestMapping("/file")
public class FileUploadController {
  @PostMapping("upload")
  public Result<String> upload(MultipartFile file) throws IOException {

    String originalFilename = file.getOriginalFilename();
    String filename = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
    file.transferTo(new File("/Users/gaojinlong/ThisMac/coding/java/big-event/files/" + filename));

    return Result.success();
  }
}
