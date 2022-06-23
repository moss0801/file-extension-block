package com.moss.fileextensionblock;

import com.moss.fileextensionblock.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class FileExtensionBlockApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileExtensionBlockApplication.class, args);
    }

}
