package com.demo.mpmultidb.config;

import com.baomidou.dynamic.datasource.provider.DynamicDataSourceProvider;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 吴邪
 * @date 2020/8/24 11:08
 */
@Service
@Primary
public class DynamicDataSourceProviderImpl implements DynamicDataSourceProvider {

    /**
     * 配置文件数据的松散绑定
     */
    private final DynamicDataSourceProperties properties;
    /**
     * Atomikos驱动数据源创建
     */
    private final AtomikosDataSourceCreator atomikosDataSourceCreator;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public DynamicDataSourceProviderImpl(DynamicDataSourceProperties properties, AtomikosDataSourceCreator atomikosDataSourceCreator) {
        this.properties = properties;
        this.atomikosDataSourceCreator = atomikosDataSourceCreator;
    }

    @Override
    public Map<String, DataSource> loadDataSources() {
        Map<String, DataSourceProperty> dataSourcePropertiesMap = properties.getDatasource();
        Map<String, DataSource> dataSourceMap = new HashMap<>(dataSourcePropertiesMap.size() * 2);
        for (Map.Entry<String, DataSourceProperty> item : dataSourcePropertiesMap.entrySet()) {
            String pollName = item.getKey();
            DataSourceProperty dataSourceProperty = item.getValue();
            dataSourceProperty.setPollName(pollName);
            dataSourceMap.put(pollName, atomikosDataSourceCreator.createDataSource(dataSourceProperty));
        }
        return dataSourceMap;
    }
}
