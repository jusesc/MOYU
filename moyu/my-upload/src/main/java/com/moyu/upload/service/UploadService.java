package com.moyu.upload.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 上传图片
 * @author JL
 * @date 19-4-16 下午3:52
 */
public interface UploadService {

    /**
     * 上传图片的方法
     * @param multipartFile
     * @return
     */
    public String uploadImage(MultipartFile multipartFile);
}
