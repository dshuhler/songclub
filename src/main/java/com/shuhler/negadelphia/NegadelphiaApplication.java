package com.shuhler.negadelphia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class NegadelphiaApplication {

    public static void main(String[] args) {
        SpringApplication.run(NegadelphiaApplication.class, args);
    }

}
