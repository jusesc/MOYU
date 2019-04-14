package com.moyu.gateway.advice;

import com.moyu.gateway.exception.MyException;
import com.moyu.gateway.vo.ExceptionResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 通用异常处理
 * @author JL
 * @date 19-4-9 下午11:57
 */
@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(MyException.class)
    public ResponseEntity<ExceptionResult> handleException(MyException e) {
        return ResponseEntity.status(e.getExceptionEnum().getCode())
                .body(new ExceptionResult(e.getExceptionEnum()));
    }
}
