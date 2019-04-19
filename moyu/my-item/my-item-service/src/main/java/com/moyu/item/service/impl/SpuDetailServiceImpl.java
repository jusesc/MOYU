package com.moyu.item.service.impl;

import com.moyu.item.mapper.SpuDetailMapper;
import com.moyu.item.pojo.SpuDetail;
import com.moyu.item.service.SpuDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Spu (Standard Product Unit) 商品详情信息实现类
 * @author JL
 * @date 19-4-18 下午3:02
 */
@Service
public class SpuDetailServiceImpl implements SpuDetailService {

    @Autowired
    private SpuDetailMapper spuDetailMapper;
}
