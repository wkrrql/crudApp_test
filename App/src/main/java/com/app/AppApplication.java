package com.app;

import lombok.extern.flogger.Flogger;
import org.slf4j.ILoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppApplication {


    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

}
