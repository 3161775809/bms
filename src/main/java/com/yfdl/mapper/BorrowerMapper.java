package com.yfdl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yfdl.entity.BorrowerEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Borrower)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-03 17:44:05
 */
@Mapper
public interface BorrowerMapper extends BaseMapper<BorrowerEntity> {
}
