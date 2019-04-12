package com.moyu.common.enums;


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
    PRICE_CANNOT_BE_NULL(400, "价格不能为空!"),
    CATEGORY_NOT_FOUND(404, "商品分类未找到"),
    ;
    private int code;
    private String msg;

}
