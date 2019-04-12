package com.moyu.item.service;

import com.moyu.common.enums.ExceptionEnum;
import com.moyu.common.exception.MyException;
import com.moyu.item.mapper.CategoryMapper;
import com.moyu.item.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author JL
 * @date 19-4-12 下午6:45
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 根据父节点id查询商品分类
     * @param pid
     * @return
     */
    public List<Category> queryCategoryByPid(Long pid) {
        // 查询条件, mapper会把对象中的非空属性作为查询条件
        Category category = new Category();
        category.setParentId(pid);
        List<Category> categories = categoryMapper.select(category);
        // 判断结果
        // CollectionUtils.isEmpty(list) 等于 list == null || list.isEmpty()
        if (CollectionUtils.isEmpty(categories)){
            throw new MyException(ExceptionEnum.CATEGORY_NOT_FOUND);
        }
        return categories;
    }
}
