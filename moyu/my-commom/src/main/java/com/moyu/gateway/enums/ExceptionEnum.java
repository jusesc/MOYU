package com.moyu.gateway.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 通用异常处理枚举类
 * @author JL
 * @date 19-4-10 上午12:10
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionEnum {
    BRAND_NOT_FOUND(404, "未找到对应厂商!"),
    CATEGORY_NOT_FOUND(404, "商品分类未找到!"),
    BRAND_SAVE_ERROR(500, "新增厂商失败!"),
    INVALID_FILE_TYPE(500, "无效的文件类型!"),
    UPLOAD_FILE_ERROR(500, "文件上传失败!")
    ;
    private int code;
    private String msg;

}
