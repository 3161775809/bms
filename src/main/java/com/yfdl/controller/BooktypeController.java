package com.yfdl.controller;




import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yfdl.entity.BookEntity;
import com.yfdl.entity.BooktypeEntity;
import com.yfdl.service.BooktypeService;
import com.yfdl.utils.QueryPageBean;
import com.yfdl.utils.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * (Booktype)表控制层
 *
 * @author makejava
 * @since 2022-11-03 16:59:00
 */
@RestController
@RequestMapping("/booktype")
public class BooktypeController{
    /**
     * 服务对象
     */
    @Resource
    private BooktypeService booktypeService;


    @GetMapping("selectBooktypeAll")
    public R selectAll() {
        return R.successResult(booktypeService.list());
    }

    @GetMapping("selectBooktype")
    public R selectBook(@RequestParam(required = false) String type){
        LambdaQueryWrapper<BooktypeEntity> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Objects.nonNull(type),BooktypeEntity::getType,type);
        BooktypeEntity one = booktypeService.getOne(lqw);
        return R.successResult(one);
    }


    @PostMapping("selectBooktypePage")
    public R selectPage(@RequestBody QueryPageBean queryPageBean){
        IPage<BooktypeEntity> page = booktypeService.getPage(queryPageBean);
        return R.successResult(page);
    }

    @PostMapping("insertBooktype")
    public R insertBook(@RequestBody BooktypeEntity booktypeEntity){
        return R.successResult(booktypeService.save(booktypeEntity));
    }

    @PostMapping("updateBooktype")
    public R updateBook(@RequestBody BooktypeEntity booktypeEntity){
        LambdaQueryWrapper<BooktypeEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BooktypeEntity::getType,booktypeEntity.getType());

        return R.successResult(booktypeService.update(booktypeEntity,queryWrapper));
    }

    @GetMapping("deleteBooktype")
    public R deleteBook(@RequestParam String type){
        LambdaQueryWrapper<BooktypeEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BooktypeEntity::getType,type);
        return R.successResult(booktypeService.remove(queryWrapper));
    }
}

