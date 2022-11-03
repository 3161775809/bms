package com.yfdl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import  com.yfdl.service.BorrowerService;
import com.yfdl.mapper.BorrowerMapper;
import com.yfdl.entity.BorrowerEntity;
import org.springframework.stereotype.Service;

/**
 * (Borrower)表服务实现类
 *
 * @author makejava
 * @since 2022-11-03 17:44:05
 */
@Service
public class BorrowerServiceImpl extends ServiceImpl<BorrowerMapper, BorrowerEntity> implements BorrowerService {
}
