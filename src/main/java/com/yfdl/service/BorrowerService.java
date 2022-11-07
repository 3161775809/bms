package com.yfdl.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yfdl.Dto.InsertUserDto;
import com.yfdl.entity.BorrowerEntity;
import com.yfdl.utils.QueryPageBean;
import com.yfdl.utils.R;

/**
 * (Borrower)表服务接口
 *
 * @author makejava
 * @since 2022-11-03 17:44:05
 */
public interface BorrowerService extends IService<BorrowerEntity> {
    IPage<BorrowerEntity> getPage(QueryPageBean queryPageBean);
}
