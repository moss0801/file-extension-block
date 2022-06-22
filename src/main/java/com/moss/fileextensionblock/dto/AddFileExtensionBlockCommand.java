package com.moss.fileextensionblock.dto;

import com.moss.fileextensionblock.domain.model.FileExtensionBlock;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 파일 확장자 차단 추가 Command
 */
@Data
public class AddFileExtensionBlockCommand {
    // 파일 확장자
    @NotBlank
    @Length(max = FileExtensionBlock.Constraint.ExtensionMaxLength)
    private String extension;

    // 고정 여부
    @NotNull
    private  Boolean isFixed;

    // 활성화 여부
    @NotNull
    private Boolean isEnabled;
}
