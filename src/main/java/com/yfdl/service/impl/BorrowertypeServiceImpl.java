package com.yfdl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import  com.yfdl.service.BorrowertypeService;
import com.yfdl.mapper.BorrowertypeMapper;
import com.yfdl.entity.BorrowertypeEntity;
import org.springframework.stereotype.Service;

/**
 * (Borrowertype)表服务实现类
 *
 * @author makejava
 * @since 2022-11-03 16:16:12
 */
@Service
public class BorrowertypeServiceImpl extends ServiceImpl<BorrowertypeMapper, BorrowertypeEntity> implements BorrowertypeService {
}
