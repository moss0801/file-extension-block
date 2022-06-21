package com.moss.fileextensionblock.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@Data
@ConfigurationProperties(prefix ="app")
public class AppProperties {

    // 고정 차단 확장자 목록
    public List<String> fixedBlockExtensions = new ArrayList<>();

    // 커스텀 확장자 최대 개수
    private Integer maxCustomExtension;
}
