package com.datasource;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.atomikos.jdbc.AtomikosDataSourceBean;

//@Configuration
//@PropertySource(value = "classpath:datasource-mybatis.properties", ignoreResourceNotFound = true)
public class DataSourceMybatisSecondConfig 
{
    @Value("${datasource.mybatis.second.unique-resource-name}")
    private String uniqueResourceName;
    @Value("${datasource.mybatis.second.url}")
    private String url;
    @Value("${datasource.mybatis.second.username}")
    private String user;
    @Value("${datasource.mybatis.second.password}")
    private String password;
    @Value("${datasource.mybatis.second.driver-class-name}")
    private String driverClass;
    @Value("${datasource.mybatis.second.filters}")
    private String filters;
    @Value("${datasource.mybatis.second.initialSize}")
    private int initialSize;
    @Value("${datasource.mybatis.second.maxActive}")
    private int maxActive;
    @Value("${datasource.mybatis.second.minIdle}")
    private int minIdle;
    @Value("${datasource.mybatis.second.maxWait}")
    private int maxWait;
    @Value("${datasource.mybatis.second.mapper-location}")
    private String mapperLocation;
    @Value("${mybatis.config-location}")
    private String mybatisConfigLocation;
 
    @Bean(name = "dataSourceSecond")
    @Primary
    public DataSource dataSourceSecond() throws SQLException 
    {
        DruidXADataSource dataSource = new DruidXADataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setFilters(filters);
        dataSource.setInitialSize(initialSize);
        dataSource.setMaxActive(maxActive);
        dataSource.setMaxActive(maxActive);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxWait(maxWait);
        
        AtomikosDataSourceBean adsBean = new AtomikosDataSourceBean();
        adsBean.setXaDataSource(dataSource);
        adsBean.setUniqueResourceName(uniqueResourceName);
        return adsBean;
    }

//	  打开后，分布式事务JTA失效
//    @Bean(name = "transactionManagerSecond")
//    @DependsOn({ "dataSourceSecond" })
//    @Primary
//    public DataSourceTransactionManager transactionManagerSecond() 
//    {
//        return new DataSourceTransactionManager(dataSourceSecond());
//    }
 
    @Bean(name = "sqlSessionFactorySecond")
    @DependsOn({ "dataSourceSecond" })
    @Primary
    public SqlSessionFactory sqlSessionFactorySecond() throws Exception 
    {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSourceSecond());
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocation));
        sessionFactory.setConfigLocation(new DefaultResourceLoader().getResource(mybatisConfigLocation));
        return sessionFactory.getObject();
    }

	@Bean(name = "sqlSessionTemplateSecond")
    @DependsOn({ "sqlSessionFactorySecond" })
	@Primary
	public SqlSessionTemplate sqlSessionTemplateSecond() throws Exception 
	{
		return new SqlSessionTemplate(sqlSessionFactorySecond());
	}
}