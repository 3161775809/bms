package com.yfdl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yfdl.entity.BookEntity;
import com.yfdl.entity.BooktypeEntity;
import com.yfdl.utils.QueryPageBean;

/**
 * (Booktype)表服务接口
 *
 * @author makejava
 * @since 2022-11-03 16:15:28
 */
public interface BooktypeService extends IService<BooktypeEntity> {
    public IPage<BooktypeEntity> getPage(QueryPageBean queryPageBean);

}
