package com.demo.mpmultidb.storage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.mpmultidb.order.entity.Order;
import com.demo.mpmultidb.storage.entity.Storage;


public interface IStorageService extends IService<Storage> {
    void test(Order order);
}
