package com.yfdl.controller;




import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yfdl.entity.BookEntity;
import com.yfdl.entity.BorrowerEntity;
import com.yfdl.service.BorrowerService;
import com.yfdl.utils.R;
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
public class BorrowerController{
    /**
     * 服务对象
     */
    @Resource
    private BorrowerService borrowerService;




}

