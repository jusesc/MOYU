package com.moyu.service;

import com.moyu.item.service.BrandService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author JL
 * @date 19-4-17 下午5:28
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class BrandServiceTestCase {

    @Autowired
    BrandService brandService;

    @Test
    public void deleteBrandInfo(){
        Long bid = 325400L;
        brandService.deleteBrandInfo(bid);
    }
}
