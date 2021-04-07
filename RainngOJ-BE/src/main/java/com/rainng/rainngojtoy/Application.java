package com.rainng.rainngojtoy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.rainng.rainngojtoy.dao.mapper")
@SpringBootApplication
public class Application implements CommandLineRunner {
    @Autowired
    private JerryStarter jerryStarter;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        jerryStarter.startJerryHost();
    }
}
