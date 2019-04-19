package com.moyu.item.service;

import com.moyu.item.pojo.SpecGroup;

import java.util.List;

/**
 * @author JL
 * @date 19-4-17 下午11:53
 */
public interface SpecGroupService {

    List<SpecGroup> queryGroupByCid(Long cid);
}
