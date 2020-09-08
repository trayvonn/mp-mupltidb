package com.demo.mpmultidb.order.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.mpmultidb.order.entity.Order;
import org.apache.ibatis.annotations.Mapper;


@Mapper
@DS("order")
public interface OrderMapper extends BaseMapper<Order> {
}
