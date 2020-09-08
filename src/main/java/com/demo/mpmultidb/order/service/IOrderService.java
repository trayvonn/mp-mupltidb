package com.demo.mpmultidb.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.mpmultidb.order.entity.Order;


public interface IOrderService extends IService<Order> {
    void test();
}
