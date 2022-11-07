package com.yfdl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yfdl.entity.BorrlendEntity;
import com.yfdl.utils.QueryPageBean;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Borrlend)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-03 16:15:44
 */
@Mapper
public interface BorrlendMapper extends BaseMapper<BorrlendEntity> {
    public IPage<BorrlendEntity> findPage(QueryPageBean queryPageBean);
}
