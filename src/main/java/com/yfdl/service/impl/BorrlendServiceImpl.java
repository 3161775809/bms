package com.yfdl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import  com.yfdl.service.BorrlendService;
import com.yfdl.mapper.BorrlendMapper;
import com.yfdl.entity.BorrlendEntity;
import com.yfdl.utils.QueryPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * (Borrlend)表服务实现类
 *
 * @author makejava
 * @since 2022-11-03 16:15:44
 */
@Service
public class BorrlendServiceImpl extends ServiceImpl<BorrlendMapper, BorrlendEntity> implements BorrlendService {
    @Autowired
    public BorrlendMapper borrlendMapper;
    public IPage<BorrlendEntity> getPage(QueryPageBean queryPageBean){
        Integer pageSize = queryPageBean.getPageSize();
        Integer currentPage = queryPageBean.getCurrentPage();
        String queryString = queryPageBean.getQueryString();
        QueryWrapper qw = new QueryWrapper();
        qw.like("cardNum",queryString);
        IPage<BorrlendEntity> borrlendEntityPage = new Page<>(currentPage,pageSize);
        IPage<BorrlendEntity> borrlendEntityIPage = borrlendMapper.selectPage(borrlendEntityPage,qw);
        return borrlendEntityIPage;
    }
}
