package ljs.spring.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = OneDataSourcesConfig.PACKAGES, sqlSessionFactoryRef = "oneSqlSessionFactory")
public class OneDataSourcesConfig {
    static final String PACKAGES = "ljs.spring.mapper.sid";
    private static final String MAPPER_LOCAL = "classpath:mybatis/mapper/sid/*.xml";

    @ConfigurationProperties("spring.datasource.druid.one")
    @Primary
    @Bean(name = "oneDataSource")
    public DruidDataSource druidDataSource() {
        DruidDataSource build = DruidDataSourceBuilder.create().build();
        return build;
        //不要用下面这个，不然application.yml中配置的druid的maxActive之类的属性加载不进去
        //return new DruidDataSource();
    }


    @Bean(name = "oneTransactionManager")
    @Primary
    public DataSourceTransactionManager oneTransactionManager(@Qualifier("oneDataSource") DataSource oneDataSource) {
        //return new DataSourceTransactionManager(druidDataSource());
        return new DataSourceTransactionManager(oneDataSource);
    }




    @Bean(name = "oneSqlSessionFactory")
    @Primary
    public SqlSessionFactory oneSqlSessionFactory(@Qualifier("oneDataSource") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCAL));
        return sessionFactoryBean.getObject();
    }

    @Bean(name = "oneJdbcTemplate")
    public JdbcTemplate primaryJdbcTemplate(@Qualifier("oneDataSource")DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

}
