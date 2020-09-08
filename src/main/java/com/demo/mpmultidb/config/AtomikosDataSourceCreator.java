package com.demo.mpmultidb.config;

import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.mysql.cj.jdbc.MysqlXADataSource;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @author 吴邪
 * @date 2020/8/24 11:15
 */
@Component
public class AtomikosDataSourceCreator {

    public DataSource createDataSource(DataSourceProperty dataSourceProperty) {
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        mysqlXaDataSource.setUrl(dataSourceProperty.getUrl());
        mysqlXaDataSource.setPassword(dataSourceProperty.getPassword());
        mysqlXaDataSource.setUser(dataSourceProperty.getUsername());
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXaDataSource);
        xaDataSource.setMinPoolSize(5);
        xaDataSource.setBorrowConnectionTimeout(60);
        xaDataSource.setMaxPoolSize(20);
        xaDataSource.setXaDataSourceClassName(dataSourceProperty.getDriverClassName());
        xaDataSource.setTestQuery("SELECT 1 FROM DUAL");
        xaDataSource.setUniqueResourceName(dataSourceProperty.getPollName());
        return xaDataSource;
    }
}
