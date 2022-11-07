package com.yfdl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yfdl.entity.BookEntity;
import com.yfdl.entity.BooktypeEntity;
import com.yfdl.utils.QueryPageBean;

import java.util.List;

/**
 * (Book)表服务接口
 *
 * @author makejava
 * @since 2022-11-03 16:14:33
 */
public interface BookService extends IService<BookEntity> {
    public IPage<BookEntity> getPage(QueryPageBean queryPageBean);

}
