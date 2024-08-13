package com.brendan.wordfinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = { "com.brendan.wordfinder" })
@ComponentScan(basePackages = { "com.brendan.wordfinder" })
public class WordFinderApplication {

    public static void main(String[] args) {
        SpringApplication.run(WordFinderApplication.class, args);
    }
}
