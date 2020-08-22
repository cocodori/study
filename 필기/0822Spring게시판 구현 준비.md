# 요구사항

- 사용자는 게시물을 등록할 수 있다.
- 사용자는 게시물을 조회할 수 있다.
- 사용자는 게시물을 수정할 수 있다.
- 사용자는 게시물을 삭제할 수 있다.

# 레이아웃

![](https://images.velog.io/images/cocodori/post/629b45bf-c558-4855-8ae4-9baa7be79606/image.png)

무료로 제공하는 부트스트랩을 이용할 것이다. [링크](https://startbootstrap.com/templates/sb-admin/)


# URL 설계


|URL|전송 방식|비고|
|----|-----|-----|
|/board/list|GET|전체 게시물 목록|
|/board/write|GET|게시물 등록 양식|
|/board/write|POST|게시물 등록 처리|
|/board/post|GET|게시물 조회|
|/board/modify|GET|게시물 수정 양식|
|/board/modify|POST|게시물 수정 처리|
|/board/remove|POST|게시물 삭제|

<br>

# 데이터베이스(MySQL)
```SQL
CREATE TABLE `tbl_board` (
  `bno` int NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `content` text NOT NULL,
  `writer` varchar(45) NOT NULL,
  `regdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `moddate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`bno`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
```
![](https://images.velog.io/images/cocodori/post/4042beef-62a0-490a-a566-7197cb33ed46/image.png)

# 순서

## 1. Persistence Layer 

## 2. Business Layer

## 3. Presentation Layer
