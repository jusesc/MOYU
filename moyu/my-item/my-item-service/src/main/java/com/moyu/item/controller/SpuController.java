package com.moyu.item.controller;

import com.moyu.common.vo.PageResult;
import com.moyu.item.pojo.Spu;
import com.moyu.item.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JL
 * @date 19-4-18 下午3:09
 */
@RestController
public class SpuController {

    @Autowired
    private SpuService spuService;

    @GetMapping("/spu/page")
    private ResponseEntity<PageResult<Spu>> querySupByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "saleable", required = false) Boolean saleable,
            @RequestParam(value = "key", required = false) String key
    ){
        return ResponseEntity.ok(spuService.querySupByPage(page, rows, saleable, key));
    }
}
