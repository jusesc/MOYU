package com.moyu.item.service.impl;

import com.moyu.common.enums.ExceptionEnum;
import com.moyu.common.exception.MyException;
import com.moyu.item.mapper.SpecGroupMapper;
import com.moyu.item.pojo.SpecGroup;
import com.moyu.item.service.SpecGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 规格参数实现类
 * @author JL
 * @date 19-4-18 上午9:18
 */
@Service
public class SpecGroupServiceImpl implements SpecGroupService {

    @Autowired
    private SpecGroupMapper specGroupMapper;

    @Override
    public List<SpecGroup> queryGroupByCid(Long cid) {
        // 查询条件
        SpecGroup specGroup = new SpecGroup();
        specGroup.setCid(cid);
        // 开始查询
        List<SpecGroup> specGroups = specGroupMapper.select(specGroup);
        // 判断是否查询到结果
        if (CollectionUtils.isEmpty(specGroups)) {
            throw new MyException(ExceptionEnum.SPEC_GROUP_NOT_FOUND);
        }

        return specGroups;
    }
}
