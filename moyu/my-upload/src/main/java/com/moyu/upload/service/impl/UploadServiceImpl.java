package com.moyu.upload.service.impl;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.domain.ThumbImageConfig;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.moyu.gateway.enums.ExceptionEnum;
import com.moyu.gateway.exception.MyException;
import com.moyu.upload.config.UploadProperties;
import com.moyu.upload.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**
 * 上传图片实现类
 * @author JL
 * @date 19-4-16 下午3:44
 */
@Service
@Slf4j
@EnableConfigurationProperties(UploadProperties.class)
public class UploadServiceImpl implements UploadService {
    // FastDFS中的storage组名 本次只开启一个组
    public static final String GROUPNAME = "group1/";

    @Autowired
    private FastFileStorageClient storageClient;

    @Autowired
    private ThumbImageConfig imageConfig;

    @Autowired
    private UploadProperties properties;

    // public static final List<String> ALLOW_TYPES = Arrays.asList("image/jpeg", "image/jpg", "image/bmp",  "image/png");

    public String uploadImage(MultipartFile file) {
        try {
            // 检查文件类型
            String contentType = file.getContentType();
            if (!properties.getAllowTypes().contains(contentType)) {
                throw new MyException(ExceptionEnum.INVALID_FILE_TYPE);
            }

            // 检查文件内容
            BufferedImage image = ImageIO.read(file.getInputStream());
            if (image == null) {
                throw new MyException(ExceptionEnum.INVALID_FILE_TYPE);
            }

            /*
            // 准备目标路径
            File dest = new File("/home/jl/Desktop", multipartFile.getOriginalFilename());
            // 保存文件到本地
            multipartFile.transferTo(dest);
            */

            // 上传文件到FastFDS
            String extension = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
            StorePath storePath = storageClient.uploadImageAndCrtThumbImage( file.getInputStream(), file.getSize(), extension, null);

            // 返回路径
            return properties.getBaseUrl()+ GROUPNAME + imageConfig.getThumbImagePath(storePath.getPath());
        } catch (Exception e) {
           log.error("[上传文件]上传文件失败!", e);
        }
        return null;
    }
}
