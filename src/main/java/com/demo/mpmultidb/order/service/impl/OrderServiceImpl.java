package com.demo.mpmultidb.order.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.mpmultidb.order.entity.Order;
import com.demo.mpmultidb.order.mapper.OrderMapper;
import com.demo.mpmultidb.order.service.IOrderService;
import com.demo.mpmultidb.storage.service.IStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    IStorageService storageService;

    @Override
    @Transactional
    public void test() {
        int count = RandomUtil.randomNumber();
        Double amount = Convert.toDouble(count * 27);
        Order order = new Order().setCommodityCode("123").setCount(count).setAmount(amount);
        save(order);
        storageService.test(order);
    }
}
