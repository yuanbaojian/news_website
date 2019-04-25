package com.ybj.news_website;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class NewsWebsiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewsWebsiteApplication.class, args);
    }

}
