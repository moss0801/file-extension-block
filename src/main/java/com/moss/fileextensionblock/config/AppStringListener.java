package com.moss.fileextensionblock.config;

import com.moss.fileextensionblock.domain.repository.FileExtensionBlockRepository;
import com.moss.fileextensionblock.dto.AddFileExtensionBlockCommand;
import com.moss.fileextensionblock.service.FileExtensionBlockService;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AppStringListener implements ApplicationListener<ApplicationStartedEvent> {
    private AppProperties appProperties;
    private FileExtensionBlockService fileExtensionBlockService;
    private FileExtensionBlockRepository fileExtensionBlockRepository;

    public AppStringListener(AppProperties appProperties, FileExtensionBlockService fileExtensionBlockService, 
                             FileExtensionBlockRepository fileExtensionBlockRepository) {
        this.appProperties = appProperties;
        this.fileExtensionBlockService = fileExtensionBlockService;
        this.fileExtensionBlockRepository = fileExtensionBlockRepository;
    }

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        // 초기 고정 파일 확장자 차단 추가
        appProperties.getFixedBlockExtensions().forEach(extension -> {
            extension = extension.trim().toLowerCase();
            if (!fileExtensionBlockRepository.existsByExtension(extension)) {
                // 존재하지 않으면 추가
                AddFileExtensionBlockCommand command = new AddFileExtensionBlockCommand();
                command.setExtension(extension);
                command.setIsFixed(true);
                command.setIsEnabled(false);
                fileExtensionBlockService.addFileExtensionBlock(command);    
            }
        });

    }
}
