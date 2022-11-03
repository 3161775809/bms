package com.yfdl.controller;




import com.yfdl.Dto.InsertUserDto;
import com.yfdl.entity.BorrowerEntity;
import com.yfdl.entity.UserEntity;
import com.yfdl.service.UserService;
import com.yfdl.utils.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2022-11-03 16:59:29
 */
@RestController
@RequestMapping("/user")
public class UserController{
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    @PostMapping("login")
    public R login(@RequestBody UserEntity user){
        return userService.login(user);
    }


    @PostMapping("insertUser")
    public R insertUser(@RequestBody InsertUserDto user){
        return userService.insertUser(user);
    }

    @PostMapping("updateUser")
    public R updateUser(@RequestBody BorrowerEntity user){
        return userService.updateUser(user);
    }









}

