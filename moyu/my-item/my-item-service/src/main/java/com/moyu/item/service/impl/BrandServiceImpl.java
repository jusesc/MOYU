package com.moyu.item.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moyu.gateway.enums.ExceptionEnum;
import com.moyu.gateway.exception.MyException;
import com.moyu.gateway.vo.PageResult;
import com.moyu.item.mapper.BrandMapper;
import com.moyu.item.pojo.Brand;
import com.moyu.item.service.BrandService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.print.DocFlavor;
import java.util.List;

/**
 * @author JL
 * @date 19-4-14 下午7:16
 */
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public PageResult<Brand> queryBrandByPage(Integer page, Integer rows, String sortBy, Boolean desc, String key) {
        // 分页 分页助手页数, 行数
        PageHelper.startPage(page, rows);
        // 过滤
        Example example = new Example(Brand.class);
        if (StringUtils.isNotBlank(key)) {
            example.createCriteria().andLike("name", "%" + key + "%").orEqualTo("letter", key.toUpperCase());
        }
        // 排序
        if (StringUtils.isNotBlank(sortBy)) {
            String orderByClause = sortBy + (desc ? " DESC" : " ASC");
            example.setOrderByClause(orderByClause);
        }
        // 查询
        List<Brand> list = brandMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) {
            throw new MyException(ExceptionEnum.BRAND_NOT_FOUND);
        }

        // 解析分页结果
        PageInfo<Brand> info = new PageInfo<>(list);

        return new PageResult<>(info.getTotal(), list);
    }
}
