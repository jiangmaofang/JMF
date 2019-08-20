package cn.com.jmf.learn.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.github.pagehelper.PageInterceptor;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * @author jiangmaofang
 * @date 2019/08/12 10:28
 */
@Configuration
@MapperScan(basePackages = {"cn.com.jmf.learn.my7.mapper"}, sqlSessionFactoryRef = "masterSqlSessionFactory")
public class MysqlMapperConfig {

    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.druid.master")
    @Bean(name = "masterDataSource")
    public DataSource systemDataSource(DruidProperties druidProperties){
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        return druidProperties.dataSource(dataSource);
    }

    @Primary
    @Bean(name = "masterSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("masterDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        List<String> typePackage = new ArrayList<>();
        typePackage.add("cn.com.jmf.learn.my7.model");
        factoryBean.setTypeAliasesPackage(StringUtils.join(typePackage));
        List<Resource> resources = new ArrayList<>();
        resources.addAll(Arrays.asList(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/**/**.xml")));
        resources.addAll(Arrays.asList(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/**.xml")));
        factoryBean.setMapperLocations(resources.toArray(new Resource[resources.size()]));
        factoryBean.setConfigLocation(new ClassPathResource("mybatis-config-mysql.xml"));
        Interceptor interceptor = new PageInterceptor();
        interceptor.setProperties(PageInterceptorProperties.createPageProperties("mysql"));
        factoryBean.setPlugins(new Interceptor[] {interceptor});
        return factoryBean.getObject();
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage("cn.com.jmf.learn.my7.mapper");
        configurer.setSqlSessionFactoryBeanName("masterSqlSessionFactory");
        Properties properties = new Properties();
        properties.setProperty("mappers", "cn.com.jmf.learn.common.mapper.Mapper");
        configurer.setProperties(properties);
        return configurer;
    }

    @Primary
    @Bean(name = "masterTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("masterDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Primary
    @Bean(name = "masterSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(
            @Qualifier("masterSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
