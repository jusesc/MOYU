package com.moyu.item.controller;

import com.moyu.item.pojo.SpecParam;
import com.moyu.item.service.SpecParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 规格参数参数表控制器
 * @author JL
 * @date 19-4-18 上午10:32
 */
@RestController
@RequestMapping("spec")
public class SpecParamController {

    @Autowired
    private SpecParamService specParamService;


    /**
     * 通过组id或cid查询参数的集合
     * @param gid 组id
     * @param cid 商品分类id
     * @param searching 判断是否搜索
     * @return
     */
    @GetMapping("params")
    public ResponseEntity<List<SpecParam>> queryParamList(
            @RequestParam(value = "gid", required = false) Long gid,
            @RequestParam(value = "cid", required = false) Long cid,
            @RequestParam(value = "searching", required = false) Boolean searching){
        return ResponseEntity.ok(specParamService.queryParamList(gid, cid, searching));
    }



}
