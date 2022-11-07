package com.yfdl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yfdl.entity.BorrlendEntity;
import com.yfdl.utils.QueryPageBean;

/**
 * (Borrlend)表服务接口
 *
 * @author makejava
 * @since 2022-11-03 16:15:44
 */
public interface BorrlendService extends IService<BorrlendEntity> {
    public IPage<BorrlendEntity> getPage(QueryPageBean queryPageBean);
}
