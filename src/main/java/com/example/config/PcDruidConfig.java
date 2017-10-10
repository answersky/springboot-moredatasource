package com.example.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author liufeng
 * @version [1.0 , 2017/10/10]
 */
@Configuration
@MapperScan(basePackages ="com.example.dao.pc", sqlSessionFactoryRef = "rdsSqlSessionFactory2")
public class PcDruidConfig {
    @Resource
    private Environment environment;

    //必须在此路径下存在xml文件，否则报错找不到文件
    static final String MAPPER_LOCATION = "classpath:mapper/pc/*.xml";


    @Bean     //声明其为Bean实例
    public DataSource dataSource2() {
        DruidDataSource datasource = new DruidDataSource();

        datasource.setUrl(environment.getProperty("spring.datasource.url2"));
        datasource.setUsername(environment.getProperty("spring.datasource.username2"));
        datasource.setPassword(environment.getProperty("spring.datasource.password2"));
        datasource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name2"));

        //configuration
        datasource.setInitialSize(Integer.parseInt(environment.getProperty("spring.datasource.initialSize")));
        datasource.setMinIdle(Integer.parseInt(environment.getProperty("spring.datasource.minIdle")));
        datasource.setMaxActive(Integer.parseInt(environment.getProperty("spring.datasource.maxActive")));
        datasource.setMaxWait(Integer.parseInt(environment.getProperty("spring.datasource.maxWait")));
        datasource.setTimeBetweenEvictionRunsMillis(Long.parseLong(environment.getProperty("spring.datasource.timeBetweenEvictionRunsMillis")));
        datasource.setMinEvictableIdleTimeMillis(Long.parseLong(environment.getProperty("spring.datasource.minEvictableIdleTimeMillis")));
        datasource.setValidationQuery(environment.getProperty("spring.datasource.validationQuery"));
        datasource.setTestWhileIdle(Boolean.parseBoolean(environment.getProperty("spring.datasource.testWhileIdle")));
        datasource.setTestOnBorrow(Boolean.parseBoolean(environment.getProperty("spring.datasource.testOnBorrow")));
        datasource.setTestOnReturn(Boolean.parseBoolean(environment.getProperty("spring.datasource.testOnReturn")));
        datasource.setPoolPreparedStatements(Boolean.parseBoolean(environment.getProperty("spring.datasource.poolPreparedStatements")));
        datasource.setMaxPoolPreparedStatementPerConnectionSize(Integer.parseInt(environment.getProperty("spring.datasource.maxPoolPreparedStatementPerConnectionSize")));
        try {
            datasource.setFilters(environment.getProperty("spring.datasource.filters"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        datasource.setConnectionProperties(environment.getProperty("spring.datasource.connectionProperties"));

        return datasource;
    }

    @Bean(name = "rdsTransactionManager2")
    public DataSourceTransactionManager rdsTransactionManager() {
        return new DataSourceTransactionManager(dataSource2());
    }

    @Bean(name = "test1SqlSessionTemplate2")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("rdsSqlSessionFactory2") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name = "rdsSqlSessionFactory2")
    public SqlSessionFactory rdsSqlSessionFactory(@Qualifier("dataSource2") DataSource rdsDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(rdsDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(PcDruidConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }

}
