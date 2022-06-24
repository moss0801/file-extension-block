# 파일 확장자 차단

https://moss-file-extension-block.herokuapp.com/

## 기술 스택
* Front End : vue.js
* Back End : spring boot
* DB : h2

## DB
```
create table file_extension_blocks (
    extension varchar(20) not null,
    is_enabled boolean not null,
    is_fixed boolean not null,
    primary key (extension)
)
```

## API
|Method|Url| Desc                    |
|---|---|-------------------------|
|POST|/fileExtensionBlocks| 파일 확장자 차단 추가            |
|GET|/fileExtensionBlocks| 파일 확장자 차단 목록 조회         |
|PUT|/fileExtensionBlocks/{extension}| 파일 확장자 수정, 고정 차단 확장자 수정 |
|DELETE|/fileExtensionBlocks/{extension}| 파일 확장자 삭제|

## 고려 사항

* 커스텀 확장자 중복 체크
  * 동일한 커스텀 확장자 추가 예외처리
  * sh 추가 후, 다시 sh 추가시 에러발생
* 커스텀 확장자는 소문자로 저장 처리
  * sh, SH 2개의 입력 모두 sh 로 저장하여 중복 제거
* 고정 확장자와 커스텀 확장자를 하나의 Table에 저장
  * 추후 발생할 수 있는 요구사항 고려
    * 고정 확장자 추가 및 삭제
    * 커스텀 확장자를 고정 확장자로 변경
    * 고정 확장자를 커스텀 확장자로 변경
    * 커스텀 확장자 비활성화 (등록 유지 및 비활성화)
* 파일 확장자 차단 목록 조회시 활성화 여부 필터 제공
  * 운영 페이지에서는 등록된 모든 목록 조회
  * 파일 업로드 시에는 활성화된 확장자 목록만 조회하여 사용
* @ControllerAdvice를 통한 공통 에러 처리
* @Valid를 이용한 유효성 검사

