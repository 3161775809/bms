package com.yfdl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yfdl.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * (User)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-03 16:16:28
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
}
