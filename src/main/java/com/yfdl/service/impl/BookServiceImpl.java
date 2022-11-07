package com.yfdl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yfdl.entity.BooktypeEntity;
import  com.yfdl.service.BookService;
import com.yfdl.mapper.BookMapper;
import com.yfdl.entity.BookEntity;
import com.yfdl.utils.QueryPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Book)表服务实现类
 *
 * @author makejava
 * @since 2022-11-03 16:14:36
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, BookEntity> implements BookService {
    @Autowired
    private BookMapper bookMapper;
    public IPage<BookEntity> getPage(QueryPageBean queryPageBean) {
        Integer pageSize = queryPageBean.getPageSize();
        Integer currentPage = queryPageBean.getCurrentPage();
        String queryString = queryPageBean.getQueryString();
        QueryWrapper qw = new QueryWrapper();
        qw.like("bookname",queryString);
        IPage<BookEntity> bookEntityIPage = new Page<>(currentPage,pageSize);
        IPage<BookEntity> bookEntities = bookMapper.selectPage(bookEntityIPage,qw);
        return bookEntities;
    }

}
