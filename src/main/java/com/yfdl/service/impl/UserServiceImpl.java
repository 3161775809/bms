package com.yfdl.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yfdl.Dto.InsertUserDto;
import com.yfdl.entity.BorrowerEntity;
import com.yfdl.service.BorrowerService;
import  com.yfdl.service.UserService;
import com.yfdl.mapper.UserMapper;
import com.yfdl.entity.UserEntity;
import com.yfdl.utils.R;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2022-11-03 16:16:28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Resource
    private BorrowerService borrowerService;

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
            BorrowerEntity borrowerInfo = borrowerService.query().eq("cardname", username).one();


            return R.successResult(borrowerInfo);
        }

    }

    @Override
    @Transactional
    public R insertUser(InsertUserDto user) {

        UserEntity userEntity = new UserEntity();
        BeanUtil.copyProperties(user,userEntity,false);

        BorrowerEntity borrower = new BorrowerEntity();
        BeanUtil.copyProperties(user,borrower,false);
        borrower.setCardname(user.getUser());

        boolean save = save(userEntity);
        boolean save1 = borrowerService.save(borrower);

        return R.successResult();
    }

    @Override
    public R updateUser(BorrowerEntity user) {
        LambdaQueryWrapper<BorrowerEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BorrowerEntity::getCardnum,user.getCardnum());
        boolean b = borrowerService.update(user,queryWrapper);
        return R.successResult();
    }
}
