package com.future.wms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

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

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        /**
         * 单个数据大小
         */
        factory.setMaxFileSize(DataSize.parse("102400KB"));
        /**
         * 总上传数据大小6
         */
        factory.setMaxRequestSize(DataSize.parse("102400KB"));
        return factory.createMultipartConfig();
    }

}
