package com.yfdl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yfdl.entity.BookEntity;
import com.yfdl.service.BooktypeService;
import com.yfdl.mapper.BooktypeMapper;
import com.yfdl.entity.BooktypeEntity;
import com.yfdl.utils.QueryPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * (Booktype)表服务实现类
 *
 * @author makejava
 * @since 2022-11-03 16:15:28
 */
@Service
public class BooktypeServiceImpl extends ServiceImpl<BooktypeMapper, BooktypeEntity> implements BooktypeService {
    @Autowired
    private BooktypeMapper booktypeMapper;
    public IPage<BooktypeEntity> getPage(QueryPageBean queryPageBean) {
        Integer pageSize = queryPageBean.getPageSize();
        Integer currentPage = queryPageBean.getCurrentPage();
        String queryString = queryPageBean.getQueryString();
        QueryWrapper qw = new QueryWrapper();
        qw.like("type",queryString);
        IPage<BooktypeEntity> bookEntityIPage = new Page<>(currentPage,pageSize);
        IPage<BooktypeEntity> bookEntities = booktypeMapper.selectPage(bookEntityIPage,qw);
        return bookEntities;
    }
}
