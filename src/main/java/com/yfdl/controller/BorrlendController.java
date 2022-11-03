package com.yfdl.controller;




import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yfdl.entity.BookEntity;
import com.yfdl.entity.BorrlendEntity;
import com.yfdl.service.BorrlendService;
import com.yfdl.utils.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (Borrlend)表控制层
 *
 * @author makejava
 * @since 2022-11-03 16:59:07
 */
@RestController
@RequestMapping("/borrlend")
public class BorrlendController{
    /**
     * 服务对象
     */
    @Resource
    private BorrlendService borrlendService;


    @GetMapping("selectBorrLend")
    public R selectAll(){

        return R.successResult(borrlendService.list());
    }

    @PostMapping("insertBorrLend")
    public R insertBook(@RequestBody BorrlendEntity borrlendEntity){
        return R.successResult(borrlendService.save(borrlendEntity));
    }

    @PostMapping("updateBorrLend")
    public R updateBook(@RequestBody BorrlendEntity borrlendEntity){

        LambdaQueryWrapper<BorrlendEntity> qeryWrapper = new LambdaQueryWrapper<>();
        qeryWrapper.eq(BorrlendEntity::getBooknum,borrlendEntity.getBooknum());
        qeryWrapper.eq(BorrlendEntity::getCardnum,borrlendEntity.getCardnum());
        qeryWrapper.eq(BorrlendEntity::getBorrowday,borrlendEntity.getBorrowday());

        return R.successResult(borrlendService.update(borrlendEntity,qeryWrapper));
    }




}

