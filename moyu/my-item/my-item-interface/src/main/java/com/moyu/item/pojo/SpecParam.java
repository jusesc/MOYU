package com.moyu.item.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author JL
 * @date 19-4-18 上午10:21
 */
@Data
@Table(name = "tb_spec_param")
public class SpecParam {
    @Id
    @KeySql(useGeneratedKeys = true)
   private long id;
   private long cid;
   private long groupid;
   private String name;
   @Column(name = "`numeric`") //numeric 是关键字 防止冲突
   private Boolean numeric;
   private String unit;
   private Boolean generic;
   private Boolean searching;
   private String segments;
}
