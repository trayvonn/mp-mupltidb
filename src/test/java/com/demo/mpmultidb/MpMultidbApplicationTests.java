package com.demo.mpmultidb;

import com.demo.mpmultidb.order.service.IOrderService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class MpMultidbApplicationTests {

    @Autowired
    IOrderService orderService;

    @Test
    void contextLoads() {
//        List<Order> list = orderService.list();
//        System.out.println(list);
        orderService.test();
    }

}
