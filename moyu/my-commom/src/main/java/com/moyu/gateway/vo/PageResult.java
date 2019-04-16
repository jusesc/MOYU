package com.moyu.gateway.vo;

import lombok.Data;

import java.util.List;

/**
 * 分页查询结果封装类
 * @author JL
 * @date 19-4-14 下午7:00
 */
@Data
public class PageResult<T> {

   private Long total; //总条数
   private Integer totalPage; // 总页数
   private List<T> items; // 当前页面数据

    public PageResult() {
    }

    public PageResult(Long total, List<T> items) {
        this.total = total;
        this.items = items;
    }

    public PageResult(Long total, Integer totalPage, List<T> items) {
        this.total = total;
        this.totalPage = totalPage;
        this.items = items;
    }
}
