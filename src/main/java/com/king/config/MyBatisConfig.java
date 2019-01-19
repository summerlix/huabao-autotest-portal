package com.king.config;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * MyBatis配置
 */
@Configuration
@MapperScan(value = "com.king.dao.mapper")
public class MyBatisConfig 
{

    @Bean
    public ConfigurationCustomizer configurationCustomizer(){
        
        return new ConfigurationCustomizer(){        
            @Override
            public void customize(org.apache.ibatis.session.Configuration configuration) {
                configuration.setMapUnderscoreToCamelCase(true);
            }
        };
    }
}
