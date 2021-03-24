package com.future.wms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * @author evanliu
 * @create 2021-03-23 17:00
 */
@MapperScan(basePackages = "com.future.wms.mapper")
@SpringBootApplication(scanBasePackages = "com.future")
public class WmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(WmsApplication.class, args);
    }

}
