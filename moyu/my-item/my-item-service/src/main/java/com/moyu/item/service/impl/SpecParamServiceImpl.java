package com.moyu.item.service.impl;

import com.moyu.common.enums.ExceptionEnum;
import com.moyu.common.exception.MyException;
import com.moyu.item.mapper.SpecParamMapper;
import com.moyu.item.pojo.SpecParam;
import com.moyu.item.service.SpecParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 规格参数参数表实现类
 * @author JL
 * @date 19-4-18 上午10:30
 */
@Service
public class SpecParamServiceImpl implements SpecParamService {

    @Autowired
    private SpecParamMapper specParamMapper;

    @Override
    public List<SpecParam> queryParamList(Long gid, Long cid, Boolean searching) {
        // 查询条件
        SpecParam specParam = new SpecParam();
        if (gid != null) {
            specParam.setGroupid(gid);
        }
        if (cid != null) {
            specParam.setCid(cid);
        }
        if (searching != null) {
            specParam.setSearching(searching);
        }
        // 开始查询
        List<SpecParam> params = specParamMapper.select(specParam);
        // 判断是否查询到结果
        if (CollectionUtils.isEmpty(params)) {
            throw new MyException(ExceptionEnum.SPEC_PARAM_NOT_FOUND);
        }
        return params;
    }
}
