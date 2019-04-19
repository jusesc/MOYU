package com.moyu.item.service;

import com.moyu.common.vo.PageResult;
import com.moyu.item.pojo.Brand;

import java.util.List;

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

    /**
     * 新增厂商
     * @param brand 厂商信息
     * @param cids 分类信息
     */
    void saveBrand(Brand brand, List<Long> cids);

    /**
     * 删除 tb_brand 和tb_category_brand 中的厂商数据
     * @param bid 厂商的id
     */
    void deleteBrandInfo(Long bid);

    /**
     * 通过id查询厂商名称
     * @param id
     */
    Brand queryById(Long id);

    List<Brand> queryBrandByCid(Long cid);
}
