package com.moss.fileextensionblock.domain.model;

import lombok.*;
import org.springframework.util.StringUtils;

import javax.persistence.*;

/**
 * 파일 확장자 차단
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
@ToString
//----
@Entity
@Table(name = "file_extension_blocks")
public class FileExtensionBlock {
    // 확장자
    @Id
    @Column(length = Constraint.ExtensionMaxLength, unique = true, nullable = false, updatable = false)
    private String extension;

    // 고정 확장자 여부
    @Setter
    @Column(nullable = false)
    private Boolean isFixed;

    // 활성화 여부
    @Column(nullable = false)
    private Boolean isEnabled;

    @Builder
    private FileExtensionBlock(String extension, Boolean isFixed, Boolean isEnabled) {
        this.extension = extension;
        this.isFixed = isFixed;
        setEnabled(isEnabled);
    }

    /**
     * 활성화 설정, 커스텀 확장자는 비활성화 불가
     */
    public void setEnabled(Boolean enabled) {
        if (!isFixed && !enabled) {
            throw new IllegalArgumentException("custom can not be disabled.");
        }
        isEnabled = enabled;
    }

    /**
     * 확장자 trim 및 lowercase 처리
     */
    public static String resolveExtension(String extension) {
        return StringUtils.hasText(extension) ? extension.trim().toLowerCase() : extension;
    }

    public static class Constraint {
        public static final int ExtensionMaxLength = 20;
    }
}
