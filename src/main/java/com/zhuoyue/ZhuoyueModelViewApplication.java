package com.zhuoyue;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;
import com.zhuoyue.common.db.DynamicDataSourceConfig;

 
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@Import({DynamicDataSourceConfig.class})
@MapperScan("com.zhuoyue.dao")
public class ZhuoyueModelViewApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZhuoyueModelViewApplication.class, args);
    }
}
