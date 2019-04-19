package com.moyu.item.service;

import com.moyu.item.pojo.Category;

import java.util.List;

/**
 * @author JL
 * @date 19-4-14 下午4:50
 */
public interface CategoryService {

    /**
     * 根据父节点id查询商品分类
     * @param pid
     * @return
     */
    List<Category> queryCategoryByPid(Long pid);

    /**
     * 通过品牌id查询商品分类
     * @param bid
     * @return
     */
    List<Category> queryByBrandId(Long bid);

    /**
     * 通过多个id查找商品分类
     * @param ids
     * @return
     */
    List<Category> queryByIds(List<Long> ids);
}
