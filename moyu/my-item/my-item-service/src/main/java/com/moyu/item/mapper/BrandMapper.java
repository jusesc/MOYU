package com.moyu.item.mapper;

import com.moyu.item.pojo.Brand;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author JL
 * @date 19-4-14 下午4:47
 */
public interface BrandMapper extends Mapper<Brand> {
    /**
     * 由中间表插入数据
     * @param cid 商品分类id
     * @param bid 厂商id
     * @return
     */
    @Insert("INSERT INTO tb_category_brand (category_id, brand_id) VALUES (#{cid},#{bid})")
    int insertCategoryBrand(@Param("cid") Long cid, @Param("bid") Long bid);


    /**
     * 删除 tb_category_brand 中间表中的关联数据
     * @param bid
     * @return
     */
    @Delete("DELETE FROM tb_category_brand WHERE brand_id=#{bid}")
    int deleteByBrandId(@Param("bid") Long bid);

    /**
     * 通过中间表查询对应cid的所有厂商
     * @param cid
     * @return
     */
    @Select("SELECT b.* FROM tb_category_brand cb left join tb_brand b on cb.brand_id = b.id WHERE cb.category_id = #{cid}")
    List<Brand> queryByCategoryId(@Param("cid") Long cid);
}
