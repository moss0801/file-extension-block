package com.moss.fileextensionblock.domain.repository;

import com.moss.fileextensionblock.domain.model.FileExtensionBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 파일 확장자 차단 Repository
 */
@Repository
public interface FileExtensionBlockRepository extends JpaRepository<FileExtensionBlock, Long> {
    /**
     * 확장자 존재 여부
     */
    boolean existsByExtension(String extension);

    /**
     * 확장자로 조회
     */
    Optional<FileExtensionBlock> findByExtension(String extension);

    /**
     * 목록 조회, 확장자 오름차순
     */
    List<FileExtensionBlock> findAllByOrderByExtensionAsc();

    /**
     * 커스텀 수 조회
     */
    int countByIsFixedFalse();
}
