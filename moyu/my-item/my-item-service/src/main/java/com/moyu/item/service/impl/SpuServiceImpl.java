package com.moyu.item.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moyu.common.enums.ExceptionEnum;
import com.moyu.common.exception.MyException;
import com.moyu.common.vo.PageResult;
import com.moyu.item.mapper.SpuMapper;
import com.moyu.item.pojo.Brand;
import com.moyu.item.pojo.Category;
import com.moyu.item.pojo.Spu;
import com.moyu.item.service.BrandService;
import com.moyu.item.service.CategoryService;
import com.moyu.item.service.SpuService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Spu (Standard Product Unit) 商品基本信息实现类
 * @author JL
 * @date 19-4-18 下午3:02
 */
@Service
public class SpuServiceImpl implements SpuService {

    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BrandService brandService;

    @Override
    public PageResult<Spu> querySupByPage(Integer page, Integer rows, Boolean saleable, String key) {
        // 分页 分页助手传页数,行数
        PageHelper.startPage(page, rows);
        // 过滤 利用Example对象来创建sql语句
        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(key)) {
            criteria.andLike("name", "%" + key + "%");
        }

        // 上下架过滤
        if (saleable != null) {
            criteria.orEqualTo("saleable", saleable);
        }
        // 默排序
        example.setOrderByClause("last_update_time DESC");

        // 查询 调用selectByExample()方法传入example可以执行上面创建好的sql语句
        List<Spu> spus = spuMapper.selectByExample(example);
        // 判断是否空
        if (CollectionUtils.isEmpty(spus)) {
            throw new MyException(ExceptionEnum.GOODS_NOT_FOUND);
        }

        //解析分类和厂商的名称
        loadCategoryAndBrandName(spus);

        // 解析分页结果
        PageInfo<Spu> info = new PageInfo<>(spus);

        return new PageResult<>(info.getTotal(), spus);
    }

    /**
     * 解析分页和厂商的名称
     * @param spus
     */
    private void loadCategoryAndBrandName(List<Spu> spus) {
        for (Spu spu : spus) {
            // 处理分类名称
            List<Category> categories = categoryService.queryByIds(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()));
            // 转换成流获取名字在变成名字的集合
            List<String> names = categories.stream().map(Category::getName).collect(Collectors.toList());
            // 把集合拼接成字符串
            spu.setCname(StringUtils.join(names, "/"));
            // 处理厂商名称
            Brand brand = brandService.queryById(spu.getBrandId());
            spu.setBname(brand.getName());
        }
    }
}
