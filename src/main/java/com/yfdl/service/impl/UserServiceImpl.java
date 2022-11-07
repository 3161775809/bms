package com.yfdl.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import  com.yfdl.service.UserService;
import com.yfdl.mapper.UserMapper;
import com.yfdl.entity.UserEntity;
import com.yfdl.utils.QueryPageBean;
import com.yfdl.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2022-11-03 16:16:28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public R login(UserEntity user) {
        //1.判断 用户名 密码 是否为空
        String username=user.getUser();
        String password=user.getPasswd();

        if(username==null || password == null){
            return R.errorResult(406,"参数错误");
        }

        //2. 查询用户是否存在
        LambdaQueryWrapper<UserEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserEntity::getUser,username);
        queryWrapper.eq(UserEntity::getPasswd,password);
        UserEntity userEntity = getOne(queryWrapper);
        if(ObjectUtil.isEmpty(userEntity)){ //不存在返回500
            return R.successResult(500,"用户名或密码错误！");
        }else {
            // 3.存在返回用户信息;
            return R.successResult(userEntity);
        }
    }

    @Override
    public IPage<UserEntity> findPage(QueryPageBean queryPageBean) {
        Integer pageSize = queryPageBean.getPageSize();
        Integer currentPage = queryPageBean.getCurrentPage();
        String queryString = queryPageBean.getQueryString();
        QueryWrapper qw = new QueryWrapper();
        qw.like("user",queryString);
        IPage<UserEntity> userEntityPage = new Page<>(currentPage,pageSize);
        IPage<UserEntity> userEntityIPage = userMapper.selectPage(userEntityPage,qw);
        return userEntityIPage;
    }
}
