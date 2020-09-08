package com.demo.mpmultidb.storage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.mpmultidb.order.entity.Order;
import com.demo.mpmultidb.storage.entity.Storage;
import com.demo.mpmultidb.storage.mapper.StorageMapper;
import com.demo.mpmultidb.storage.service.IStorageService;
import org.springframework.stereotype.Service;

@Service
public class StorageServiceImpl extends ServiceImpl<StorageMapper, Storage> implements IStorageService {

    @Override
    public void test(Order order) {
        Storage storage = new Storage().setId(1L).setCommodityCode(order.getCommodityCode()).setCount(order.getCount());
        saveOrUpdate(storage);
    }
}
