package com.yfdl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import  com.yfdl.service.BookService;
import com.yfdl.mapper.BookMapper;
import com.yfdl.entity.BookEntity;
import org.springframework.stereotype.Service;

/**
 * (Book)表服务实现类
 *
 * @author makejava
 * @since 2022-11-03 16:14:36
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, BookEntity> implements BookService {
}
