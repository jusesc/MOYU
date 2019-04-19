package com.moyu.item.service;

import com.moyu.common.vo.PageResult;
import com.moyu.item.pojo.Spu;

/**
 * @author JL
 * @date 19-4-18 下午3:01
 */
public interface SpuService {

    PageResult<Spu> querySupByPage(Integer page, Integer rows, Boolean saleable, String key);
}
