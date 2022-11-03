package com.yfdl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import  com.yfdl.service.BorrlendService;
import com.yfdl.mapper.BorrlendMapper;
import com.yfdl.entity.BorrlendEntity;
import org.springframework.stereotype.Service;

/**
 * (Borrlend)表服务实现类
 *
 * @author makejava
 * @since 2022-11-03 16:15:44
 */
@Service
public class BorrlendServiceImpl extends ServiceImpl<BorrlendMapper, BorrlendEntity> implements BorrlendService {
}
