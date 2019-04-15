package com.moyu.item.service;

import com.moyu.gateway.vo.PageResult;
import com.moyu.item.pojo.Brand;

/**
 * @author JL
 * @date 19-4-14 下午4:53
 */
public interface BrandService {

    /**
     * 按条件分页查询
     * @param page 页数
     * @param rows 行数
     * @param sortBy 排序
     * @param desc 降序
     * @param key 关键字
     * @return
     */
    PageResult<Brand> queryBrandByPage(Integer page, Integer rows, String sortBy, Boolean desc, String key);
}
