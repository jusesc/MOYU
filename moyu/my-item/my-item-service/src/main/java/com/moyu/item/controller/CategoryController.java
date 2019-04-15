package com.moyu.item.controller;

import com.moyu.item.pojo.Category;
import com.moyu.item.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author JL
 * @date 19-4-12 下午7:07
 */
@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryService;

    /**
     * 根据父节点id查询商品分类
     * @param pid
     * @return
     */
    @GetMapping("list")
    public ResponseEntity<List<Category>> queryCategoryByPid(@RequestParam("pid") Long pid){
        return ResponseEntity.ok(categoryService.queryCategoryByPid(pid));
    }

}
