package com.moyu.gateway.exception;

import com.moyu.gateway.enums.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 自定义异常
 * @author JL
 * @date 19-4-10 上午12:08
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MyException extends RuntimeException{
    private static final long serialVersionUID = 544314809133445385L;

    private ExceptionEnum exceptionEnum;
}
