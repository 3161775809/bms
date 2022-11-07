package com.yfdl.controller;

import cn.hutool.json.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yfdl.entity.BookEntity;
import com.yfdl.service.BookService;
import com.yfdl.utils.QueryPageBean;
import com.yfdl.utils.R;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.Objects;

/**
 * (Book)表控制层
 *
 * @author makejava
 * @since 2022-11-03 16:58:40
 */
@RestController
@RequestMapping("/book")
public class BookController{
    /**
     * 服务对象
     */
    @Resource
    private BookService bookService;

    @GetMapping("selectBook")
    public R selectBook(@RequestParam(required = false) String booknum, @RequestParam(required = false) String bookname){
        LambdaQueryWrapper<BookEntity> bookEntityLambdaQueryWrapper = new LambdaQueryWrapper<>();
        bookEntityLambdaQueryWrapper.eq(Objects.nonNull(booknum),BookEntity::getBooknum,booknum);
        bookEntityLambdaQueryWrapper.eq(Objects.nonNull(bookname),BookEntity::getBookname,bookname);
        BookEntity one = bookService.getOne(bookEntityLambdaQueryWrapper);
        return R.successResult(one);
    }


    @RequestMapping ("selectAll")
    public R selectAll(@RequestBody QueryPageBean queryPageBean) {
        IPage<BookEntity> page = bookService.getPage(queryPageBean);
        String queryString = queryPageBean.getQueryString();
        return R.successResult(page);
    }

    @PostMapping("insertBook")
    public R insertBook(@RequestBody BookEntity bookEntity){
        return R.successResult(bookService.save(bookEntity));
    }

    @PutMapping("updateBook")
    public R updateBook(@RequestBody BookEntity bookEntity){
        LambdaQueryWrapper<BookEntity> bookEntityLambdaQueryWrapper = new LambdaQueryWrapper<>();
        bookEntityLambdaQueryWrapper.eq(BookEntity::getBooknum,bookEntity.getBooknum());

        return R.successResult(bookService.update(bookEntity,bookEntityLambdaQueryWrapper));
    }

    @DeleteMapping("deleteBook")
    public R deleteBook(@RequestParam String booknum) {
        LambdaQueryWrapper<BookEntity> bookEntityLambdaQueryWrapper = new LambdaQueryWrapper<>();
        bookEntityLambdaQueryWrapper.eq(BookEntity::getBooknum, booknum);
        return R.successResult(bookService.remove(bookEntityLambdaQueryWrapper));
    }

}

