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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 厂商分类实现类
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
        // 过滤 常见Excample对象来创建sql语句
        Example example = new Example(Brand.class);
        if (StringUtils.isNotBlank(key)) {
            // 模糊查询
            example.createCriteria().andLike("name", "%" + key + "%").orEqualTo("letter", key.toUpperCase());
        }
        // 排序
        if (StringUtils.isNotBlank(sortBy)) {
            String orderByClause = sortBy + (desc ? " DESC" : " ASC");
            example.setOrderByClause(orderByClause);
        }
        // 查询 调用selectByExample()方法传入example可以执行上面创建好的sql语句
        List<Brand> list = brandMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) {
            throw new MyException(ExceptionEnum.BRAND_NOT_FOUND);
        }

        // 解析分页结果
        PageInfo<Brand> info = new PageInfo<>(list);

        return new PageResult<>(info.getTotal(), list);
    }

    @Override
    @Transactional
    public void saveBrand(Brand brand, List<Long> cids) {
        // 新增厂商
        brand.setId(null);
        int count = brandMapper.insert(brand);
        // 判断返回值是否不为1
        if (count != 1) {
            // 是: 返回 500, BRAND_SAVE_ERROR 异常
            throw new MyException(ExceptionEnum.BRAND_SAVE_ERROR);
        }

        // 新增中间表
        // 遍历获取所有的商品分类cids
        for (Long cid : cids) {
            // 在brandMapper中添加insertCategoryBrand方法 利用@insert注解的形式
            brandMapper.insertCategoryBrand(cid, brand.getId());
            // 判断返回值是否不为1
            if (count != 1) {
                // 是: 返回 500, BRAND_SAVE_ERROR 异常 (此处不再添加新的异常.避免被发现还有中间表)
                throw new MyException(ExceptionEnum.BRAND_SAVE_ERROR);
            }
        }
    }


}
