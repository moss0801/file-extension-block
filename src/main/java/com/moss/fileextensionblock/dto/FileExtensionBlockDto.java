package com.moss.fileextensionblock.dto;

import com.moss.fileextensionblock.domain.model.FileExtensionBlock;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * 파일 확장자 차단 Dto
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class FileExtensionBlockDto {
    // 파일 확장자
    @Length(max = FileExtensionBlock.Constraint.ExtensionMaxLength)
    private String extension;

    // 고정 확장자 여부
    private Boolean isFixed;

    // 활성화 여부
    private Boolean isEnabled;
}
