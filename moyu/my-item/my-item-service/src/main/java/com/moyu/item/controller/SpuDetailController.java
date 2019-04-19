package com.moyu.item.controller;

import com.moyu.item.service.SpuDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JL
 * @date 19-4-18 下午3:10
 */
@RestController
public class SpuDetailController {

    @Autowired
    private SpuDetailService spuDetailService;
}
