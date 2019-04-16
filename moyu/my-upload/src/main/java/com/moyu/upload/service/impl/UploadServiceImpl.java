package com.moyu.upload.service.impl;

import com.moyu.gateway.enums.ExceptionEnum;
import com.moyu.gateway.exception.MyException;
import com.moyu.upload.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * 上传图片实现类
 * @author JL
 * @date 19-4-16 下午3:44
 */
@Service
@Slf4j
public class UploadServiceImpl implements UploadService {

    public static final List<String> ALLOW_TYPES = Arrays.asList("image/jpeg", "image/jpg", "image/bmp",  "image/png");

    public String uploadImage(MultipartFile multipartFile) {
        try {
            // 检查文件类型
            String contentType = multipartFile.getContentType();
            if (!ALLOW_TYPES.contains(contentType)) {
                throw new MyException(ExceptionEnum.INVALID_FILE_TYPE);
            }

            // 检查文件内容
            BufferedImage image = ImageIO.read(multipartFile.getInputStream());
            if (image == null) {
                throw new MyException(ExceptionEnum.INVALID_FILE_TYPE);
            }

            // 准备目标路径
            File dest = new File("/home/jl/Desktop", multipartFile.getOriginalFilename());
            // 保存文件到本地
            multipartFile.transferTo(dest);

            // 返回路径
            return "http://image.leyou.com/upload/" + multipartFile.getOriginalFilename();
        } catch (Exception e) {
           log.error("上传文件失败!", e);
        }
        return null;
    }
}
