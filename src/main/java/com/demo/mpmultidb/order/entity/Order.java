package com.demo.mpmultidb.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName(value = "t_order")
public class Order {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String commodityCode;
    private Integer count;
    private Double amount;
}
