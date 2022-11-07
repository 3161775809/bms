package com.yfdl.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yfdl.Dto.InsertUserDto;
import com.yfdl.entity.BookEntity;
import com.yfdl.entity.UserEntity;
import  com.yfdl.service.BorrowerService;
import com.yfdl.mapper.BorrowerMapper;
import com.yfdl.entity.BorrowerEntity;
import com.yfdl.utils.QueryPageBean;
import com.yfdl.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * (Borrower)表服务实现类
 *
 * @author makejava
 * @since 2022-11-03 17:44:05
 */
@Service
public class BorrowerServiceImpl extends ServiceImpl<BorrowerMapper, BorrowerEntity> implements BorrowerService {
    @Autowired
    private BorrowerMapper borrowerMapper;
    @Override
    public IPage<BorrowerEntity> getPage(QueryPageBean queryPageBean) {
        Integer pageSize = queryPageBean.getPageSize();
        Integer currentPage = queryPageBean.getCurrentPage();
        String queryString = queryPageBean.getQueryString();
        QueryWrapper qw = new QueryWrapper();
        qw.like("cardnum",queryString);
        IPage<BorrowerEntity> borrowerEntityIPage = new Page<>(currentPage,pageSize);
        IPage<BorrowerEntity> borrowerPage = borrowerMapper.selectPage(borrowerEntityIPage,qw);
        return borrowerPage;
    }

}
