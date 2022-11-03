package com.yfdl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yfdl.Dto.InsertUserDto;
import com.yfdl.entity.BorrowerEntity;
import com.yfdl.entity.UserEntity;
import com.yfdl.utils.R;

/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2022-11-03 16:16:28
 */
public interface UserService extends IService<UserEntity> {
    R login(UserEntity user);

    R insertUser(InsertUserDto user);

    R updateUser(BorrowerEntity user);
}
