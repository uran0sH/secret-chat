package com.hwy.secretchat;

        import org.mybatis.spring.annotation.MapperScan;
        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SecretChatApplication
 * @author huangwenyu
 */
@MapperScan(basePackages = "com.hwy.secretchat.pojo.mapper")
@SpringBootApplication
public class SecretChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecretChatApplication.class, args);
    }

}
