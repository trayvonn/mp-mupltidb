package com.demo.mpmultidb.storage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName(value = "t_storage")
public class Storage {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String commodityCode;
    private Integer count;
}
