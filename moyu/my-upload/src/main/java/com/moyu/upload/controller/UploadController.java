package com.moyu.upload.controller;

import com.moyu.upload.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author JL
 * @date 19-4-16 下午3:42
 */
@RestController
@RequestMapping("upload")
public class UploadController {

    @Autowired
    UploadService uploadService;

    /**
     * 上传图片
     * @param multipartFile
     * @return
     */
    @PostMapping("image")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile multipartFile){
        return ResponseEntity.ok(uploadService.uploadImage(multipartFile));
    }
}
