package com.yfdl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yfdl.service.BooktypeService;
import com.yfdl.mapper.BooktypeMapper;
import com.yfdl.entity.BooktypeEntity;
import org.springframework.stereotype.Service;

/**
 * (Booktype)表服务实现类
 *
 * @author makejava
 * @since 2022-11-03 16:15:28
 */
@Service
public class BooktypeServiceImpl extends ServiceImpl<BooktypeMapper, BooktypeEntity> implements BooktypeService {
}
