package com.hwy.secretchat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * SecretChatApplication
 * @author huangwenyu
 */
@MapperScan(basePackages = "com.hwy.secretchat.model.mapper")
@SpringBootApplication
public class SecretChatApplication {

    @Bean
    public SpringUtil getSpringUtil() {
        return new SpringUtil();
    }

    public static void main(String[] args) {
        SpringApplication.run(SecretChatApplication.class, args);
    }

}
