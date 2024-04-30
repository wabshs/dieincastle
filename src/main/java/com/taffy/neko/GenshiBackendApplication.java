package com.taffy.neko;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;


@SpringBootApplication
@Slf4j
public class GenshiBackendApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(GenshiBackendApplication.class, args);
        ConfigurableEnvironment environment = context.getEnvironment();
        String port = environment.getProperty("server.port");
        String serviceName = environment.getProperty("spring.application.name");
        log.info("\n---------------------------------------------\n\t" +
                "本项目已经在 {} 端口启动(>^ω^<) \n\t" +
                "项目名称为: {}\n\t" +
                "接口文档地址: localhost:{}/api/doc.html \n\t", port, serviceName, port);
    }

}
