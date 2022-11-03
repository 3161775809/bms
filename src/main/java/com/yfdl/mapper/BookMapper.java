package com.yfdl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yfdl.entity.BookEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Book)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-03 16:14:38
 */
@Mapper
public interface BookMapper extends BaseMapper<BookEntity> {
}
