package com.moyu.item.service;

import com.moyu.item.pojo.SpecParam;

import java.util.List;

/**
 * @author JL
 * @date 19-4-18 上午10:30
 */
public interface SpecParamService {
    List<SpecParam> queryParamList(Long gid, Long cid, Boolean searching);
}
