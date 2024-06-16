package com.ginlon.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ginlon.pojo.Result;
import com.ginlon.utils.AliOssUtil;

@RestController
@RequestMapping("/file")
public class FileUploadController {
  @PostMapping("upload")
  public Result<String> upload(MultipartFile file) throws Exception {
    String originalFilename = file.getOriginalFilename();
    String filename = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));

    String url = AliOssUtil.uploadFile(filename, file.getInputStream());

    return Result.success(url);
  }
}
