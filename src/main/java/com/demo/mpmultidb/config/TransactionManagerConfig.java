package com.demo.mpmultidb.config;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceProperties;
import com.baomidou.mybatisplus.autoconfigure.SpringBootVFS;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.sql.DataSource;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 吴邪
 * @date 2020/8/24 11:42
 */
@Configuration
@EnableTransactionManagement
public class TransactionManagerConfig {

    @Autowired
    AtomikosDataSourceCreator atomikosDataSourceCreator;

    @Bean(name = "userTransaction")
    public UserTransaction userTransaction() throws Throwable {
        UserTransactionImp userTransactionImp = new UserTransactionImp();
        userTransactionImp.setTransactionTimeout(10000);
        return userTransactionImp;
    }

    @Bean(name = "atomikosTransactionManager")
    public TransactionManager atomikosTransactionManager() throws Throwable {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.setForceShutdown(false);
        return userTransactionManager;
    }

    @Bean(name = "transactionManager")
    @DependsOn({"userTransaction", "atomikosTransactionManager"})
    public PlatformTransactionManager transactionManager() throws Throwable {
        return new JtaTransactionManager(userTransaction(), atomikosTransactionManager());
    }

    /*=======================以下配置DataSource和SqlSessionFactory=======================*/

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Bean(name = "orderDB")
    public DataSource orderDB(DynamicDataSourceProperties properties) {
        Map<String, DataSourceProperty> datasource = properties.getDatasource();
        DataSourceProperty dataSourceProperty = datasource.get("order");
        return atomikosDataSourceCreator.createDataSource(dataSourceProperty);
    }

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Bean(name = "storageDB")
    public DataSource storageDB(DynamicDataSourceProperties properties) {
        Map<String, DataSourceProperty> datasource = properties.getDatasource();
        DataSourceProperty dataSourceProperty = datasource.get("storage");
        return atomikosDataSourceCreator.createDataSource(dataSourceProperty);
    }


    @Bean(name = "orderSqlSessionFactory")
    public SqlSessionFactory orderSqlSessionFactory(@Qualifier("orderDB") DataSource dataSource) throws Exception {
        return buildSqlSessionFactory(dataSource);
    }

    @Primary
    @Bean(name = "storageSqlSessionFactory")
    public SqlSessionFactory storageSqlSessionFactory(@Qualifier("storageDB") DataSource dataSource) throws Exception {
        return buildSqlSessionFactory(dataSource);
    }

    @Bean(name = "sqlSessionTemplate")
    public CustomSqlSessionTemplate sqlSessionTemplate(@Qualifier("orderSqlSessionFactory") SqlSessionFactory orderSqlSessionFactory,
                                                       @Qualifier("storageSqlSessionFactory") SqlSessionFactory storageSqlSessionFactory){
        Map<Object, SqlSessionFactory> sqlSessionFactoryMap = new HashMap<>();
        sqlSessionFactoryMap.put("order",orderSqlSessionFactory);
        sqlSessionFactoryMap.put("storage",storageSqlSessionFactory);
        CustomSqlSessionTemplate customSqlSessionTemplate = new CustomSqlSessionTemplate(storageSqlSessionFactory);
        customSqlSessionTemplate.setTargetSqlSessionFactorys(sqlSessionFactoryMap);
        customSqlSessionTemplate.setDefaultTargetSqlSessionFactory(storageSqlSessionFactory);
        return customSqlSessionTemplate;
    }

    private SqlSessionFactory buildSqlSessionFactory(DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setVfs(SpringBootVFS.class);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/**/*.xml"));
        return bean.getObject();
    }
}
