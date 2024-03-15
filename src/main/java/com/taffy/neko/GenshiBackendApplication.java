package com.taffy.neko;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
@Slf4j
@MapperScan("com.taffy.neko.mapper")
public class GenshiBackendApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(GenshiBackendApplication.class, args);
        ConfigurableEnvironment environment = context.getEnvironment();
        String port = environment.getProperty("server.port");
        String serviceName = environment.getProperty("spring.application.name");
        log.info("本项目已经在 " + port + " 端口启动(>^ω^<) 项目名称为: " + serviceName);
    }

}
