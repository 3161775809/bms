package com.yfdl.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yfdl.entity.BookEntity;
import com.yfdl.entity.BorrowerEntity;
import com.yfdl.service.BorrowerService;
import com.yfdl.utils.QueryPageBean;
import com.yfdl.utils.R;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * (Borrower)表控制层
 *
 * @author makejava
 * @since 2022-11-03 17:44:05
 */
@RestController
@RequestMapping("/borrower")
public class BorrowerController {
    /**
     * 服务对象
     */
    @Resource
    private BorrowerService borrowerService;

    @DeleteMapping("/deleteBorrower")
    public R deleteBorrower(@RequestBody BorrowerEntity borrower) {
        LambdaQueryWrapper<BorrowerEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BorrowerEntity::getCardnum,borrower.getCardnum());
        boolean b = borrowerService.remove(queryWrapper);
        return R.successResult();
    }

    @PutMapping("/updateBorrower")
    public R updateBorrower(@RequestBody BorrowerEntity borrower) {
        LambdaQueryWrapper<BorrowerEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BorrowerEntity::getCardnum,borrower.getCardnum());
        boolean b = borrowerService.update(borrower,queryWrapper);
        return R.successResult();
    }

    @PostMapping("/insertBorrower")
    public R insertBorrower(@RequestBody  BorrowerEntity borrower) {
        return R.successResult(borrowerService.save(borrower));
    }
    @GetMapping("/findAll")
    public R findAll() {
        return R.successResult(borrowerService.list());
    }

    @PostMapping("/findPage")
    public R findPage(@RequestBody QueryPageBean queryPageBean) {
        IPage<BorrowerEntity> page = borrowerService.getPage(queryPageBean);
        String queryString = queryPageBean.getQueryString();
        return R.successResult(page);
    }
}

