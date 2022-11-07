package com.yfdl.controller;




import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yfdl.Dto.InsertUserDto;
import com.yfdl.entity.BorrowerEntity;
import com.yfdl.entity.UserEntity;
import com.yfdl.service.UserService;
import com.yfdl.utils.QueryPageBean;
import com.yfdl.utils.R;
import org.apache.catalina.User;
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
    public R insertUser(@RequestBody UserEntity user){
        return R.successResult(userService.save(user));
    }

    @PostMapping("updateUser")
    public R updateUser(@RequestBody UserEntity user){
        QueryWrapper qw = new QueryWrapper();
        qw.eq("user",user.getUser());
        return R.successResult(userService.update(user,qw));
    }

    @GetMapping("findAllUser")
    public R findAllUser() {
        return R.successResult(userService.list());
    }

    @PostMapping("deleteUser")
    public R deleteUser(@RequestBody UserEntity user) {
        QueryWrapper qw = new QueryWrapper();
        qw.eq("user",user.getUser());
        return R.successResult(userService.remove(qw));
    }

    @PostMapping("findPage")
    public R findPage(@RequestBody QueryPageBean queryPageBean) {
        return R.successResult(userService.findPage(queryPageBean));
    }








}

