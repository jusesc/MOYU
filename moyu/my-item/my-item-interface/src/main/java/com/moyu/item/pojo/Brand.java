package com.moyu.item.pojo;


import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author JL
 * @date 19-4-14 下午4:43
 */
@Data
@Table(name = "tb_brand")
public class Brand {
    @Id
    @KeySql(useGeneratedKeys=true)
   private Long id;
   private String name;// 厂商名字
   private String image;// 厂商图片
   private Character letter;
}
