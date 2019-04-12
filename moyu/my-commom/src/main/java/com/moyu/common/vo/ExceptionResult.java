package com.moyu.common.vo;

import com.moyu.common.enums.ExceptionEnum;
import lombok.Data;

/**
 * @author JL
 * @date 19-4-10 下午12:31
 */
@Data
public class ExceptionResult {
    private int status;
    private String message;
    private Long timestamp;

    public ExceptionResult(ExceptionEnum em) {
        this.status = em.getCode();
        this.message = em.getMsg();
        this.timestamp = System.currentTimeMillis();
    }
}
