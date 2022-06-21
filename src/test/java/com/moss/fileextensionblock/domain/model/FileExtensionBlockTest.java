package com.moss.fileextensionblock.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FileExtensionBlockTest {

    @Test
    public void new_If_isFixed_true_isEnable_true() {
        // Arrange & Act
        FileExtensionBlock model = FileExtensionBlock.builder()
                .extension("abc")
                .isFixed(true)
                .isEnabled(true)
                .build();

        // Assert
        Assertions.assertNotNull(model);
    }

    @Test
    public void new_If_isFixed_true_isEnable_false() {
        // Arrange & Act
        FileExtensionBlock model = FileExtensionBlock.builder()
                .extension("abc")
                .isFixed(true)
                .isEnabled(false)
                .build();

        // Assert
        Assertions.assertNotNull(model);
    }

    @Test
    public void new_If_isFixed_false_isEnable_true() {
        // Arrange & Act
        FileExtensionBlock model = FileExtensionBlock.builder()
                .extension("abc")
                .isFixed(false)
                .isEnabled(true)
                .build();

        // Assert
        Assertions.assertNotNull(model);
    }

    @Test
    public void new_If_isFixed_false_isEnable_false_Then_throw_IllegalArgumentException() {
        // // Arrange & Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            FileExtensionBlock model = FileExtensionBlock.builder()
                    .extension("abc")
                    .isFixed(false)
                    .isEnabled(false)
                    .build();
        });
    }
}
