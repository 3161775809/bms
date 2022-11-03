package com.yfdl.controller;




import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yfdl.entity.BooktypeEntity;
import com.yfdl.entity.BorrowertypeEntity;
import com.yfdl.service.BorrowertypeService;
import com.yfdl.utils.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (Borrowertype)表控制层
 *
 * @author makejava
 * @since 2022-11-03 16:59:23
 */
@RestController
@RequestMapping("/borrowertype")
public class BorrowertypeController{
    /**
     * 服务对象
     */
    @Resource
    private BorrowertypeService borrowertypeService;


    @GetMapping("selectBorrowerType")
    public R selectAll(){
        return R.successResult(borrowertypeService.list());
    }

    @PostMapping("insertBorrowerType")
    public R insertBook(@RequestBody BorrowertypeEntity borrowertypeEntity){
        return R.successResult(borrowertypeService.save(borrowertypeEntity));
    }

    @PostMapping("updateBorrowerType")
    public R updateBook(@RequestBody BorrowertypeEntity borrowertypeEntity){
        LambdaQueryWrapper<BorrowertypeEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BorrowertypeEntity::getType,borrowertypeEntity.getType());

        return R.successResult(borrowertypeService.update(borrowertypeEntity,queryWrapper));
    }

    @GetMapping("deleteBorrowerType")
    public R deleteBook(@RequestParam String type){
        LambdaQueryWrapper<BorrowertypeEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BorrowertypeEntity::getType,type);
        return R.successResult(borrowertypeService.remove(queryWrapper));
    }





}

