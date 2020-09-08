package com.demo.mpmultidb;

import com.demo.mpmultidb.order.mapper.OrderMapper;
import com.demo.mpmultidb.storage.mapper.StorageMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackageClasses = {OrderMapper.class, StorageMapper.class})
@SpringBootApplication
public class MpMultidbApplication {

    public static void main(String[] args) {
        SpringApplication.run(MpMultidbApplication.class, args);
    }

}
