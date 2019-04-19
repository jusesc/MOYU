package com.moyu.item.mapper;

import com.moyu.item.pojo.Category;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author JL
 * @date 19-4-12 下午6:37
 */                                                        // 通过多个id操作<泛型, 主键类型>
public interface CategoryMapper extends Mapper<Category> , IdListMapper<Category, Long> {
    /**
     * 根据品牌id查询商品分类
     * @param bid
     * @return
     */
    @Select("SELECT * FROM tb_category WHERE id IN (SELECT category_id FROM tb_category_brand WHERE brand_id = #{bid})")
    List<Category> queryByBrandId(Long bid);
}
