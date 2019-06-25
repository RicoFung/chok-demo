package com.datasource;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
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
//@PropertySource(value = "classpath:datasource-mybatisS.properties", ignoreResourceNotFound = true)
public class DataSourceMybatisFirstConfig 
{
    @Value("${datasource.mybatis.first.unique-resource-name}")
    private String uniqueResourceName;
    @Value("${datasource.mybatis.first.url}")
    private String url;
    @Value("${datasource.mybatis.first.username}")
    private String user;
    @Value("${datasource.mybatis.first.password}")
    private String password;
    @Value("${datasource.mybatis.first.driver-class-name}")
    private String driverClass;
    @Value("${datasource.mybatis.first.filters}")
    private String filters;
    @Value("${datasource.mybatis.first.initialSize}")
    private int initialSize;
    @Value("${datasource.first.maxActive}")
    private int maxActive;
    @Value("${datasource.mybatis.first.minIdle}")
    private int minIdle;
    @Value("${datasource.mybatis.first.maxWait}")
    private int maxWait;
    @Value("${datasource.mybatis.first.mapper-location}")
    private String mapperLocation;
    @Value("${mybatis.config-location}")
    private String mybatisConfigLocation;
 
    @Bean(name = "dataSourceFirst")
    @Primary
    public DataSource dataSourceFirst() throws SQLException 
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
//    @Bean(name = "transactionManagerFirst")
//    @DependsOn({ "dataSourceFirst" })
//    @Primary
//    public DataSourceTransactionManager transactionManagerFirst() 
//    {
//        return new DataSourceTransactionManager(dataSourceFirst());
//    }
 
    @Bean(name = "sqlSessionFactoryFirst")
    @DependsOn({ "dataSourceFirst" })
    @Primary
    public SqlSessionFactory sqlSessionFactoryFirst() throws Exception 
    {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSourceFirst());
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocation));
        sessionFactory.setConfigLocation(new DefaultResourceLoader().getResource(mybatisConfigLocation));
        return sessionFactory.getObject();
    }

	@Bean(name = "sqlSessionTemplateFirst")
    @DependsOn({ "sqlSessionFactoryFirst" })
	@Primary
	public SqlSessionTemplate sqlSessionTemplateFirst() throws Exception 
	{
		return new SqlSessionTemplate(sqlSessionFactoryFirst());
	}
}