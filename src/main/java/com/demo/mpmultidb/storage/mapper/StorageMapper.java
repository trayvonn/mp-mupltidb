package com.demo.mpmultidb.storage.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.mpmultidb.storage.entity.Storage;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@DS("storage")
public interface StorageMapper extends BaseMapper<Storage> {
}
