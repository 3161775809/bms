package com.yfdl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yfdl.entity.BooktypeEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Booktype)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-03 16:15:28
 */
@Mapper
public interface BooktypeMapper extends BaseMapper<BooktypeEntity> {
}
