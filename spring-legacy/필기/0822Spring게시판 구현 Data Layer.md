# 영속 계층 구현 순서

## VO 생성
```java
@Data
@Builder
public class BoardVO {
	private Long bno;
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private Date moddate;
}

```

## MyBatis Mapper인터페이스 & Mapper.xml
```
public interface BoardMapper {
	int insert(BoardVO boardVO);
	List<BoardVO> getAllPost();
	BoardVO getPost(Long bno);
	int update(BoardVO boardVO);
	int delete(Long bno);

	Long lastInsertId();
}

```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.coco.mapper.BoardMapper">

<select id="getAllPost" resultType="com.coco.domain.BoardVO">
	SELECT *
	FROM tbl_board
	WHERE bno > 0
	LIMIT 0, 30
</select>

<insert id="insert">
	INSERT INTO tbl_board(title, content, writer)
	VALUES (#{title}, #{content}, #{writer})
</insert>

<select id="lastInsertId" resultType="Long">
	SELECT last_insert_id()
</select>

<select id="getPost" resultType="com.coco.domain.BoardVO">
	SELECT *
	FROM tbl_board
	WHERE bno > 0
	AND bno = #{bno}
</select>

<update id="update">
	UPDATE tbl_board
	SET title = #{title}
		,content = #{content}
	WHERE bno = #{bno}
</update>

<delete id="delete">
	DELETE FROM tbl_board
	WHERE bno = #{bno}
</delete>

</mapper>
```

주의해야 할 점은 id속성이 Mapper의 이름과 동일해야 한다는 것과 기본 자료형이 아닐 경우 ResultType을 반드시 명시해야 한다는 것이다.

## Mapper Test

```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
@Log4j
public class BoardMapperTests {

	@Autowired
	BoardMapper boardMapper;
	
	@Test
	public void getAllPostTest() {
		List<BoardVO> allPostList= boardMapper.getAllPost();
		
		assertNotNull(allPostList);

		allPostList.forEach(element -> log.info(element));
	}
	
	@Test
	public void insertTest() {
		BoardVO boardVO = BoardVO.builder()
				.title("Mapper Insert Test2")
				.content("Hello MyBatis Mapper")
				.writer("user0")
				.build();
		
		int result = boardMapper.insert(boardVO);
		
		log.info("result : " + result);
		
		assertTrue(result == 1);
		
		Long lastInsertId = boardMapper.lastInsertId();
		
		log.info("LAST INSERT ID : " + lastInsertId);
	}
	
	@Test
	public void getPostTest() {
		BoardVO boardVO = boardMapper.getPost(4L);
		
		assertNotNull(boardVO);
		log.info(boardVO);
	}
	
	@Test
	public void updateTest() {
		BoardVO boardVO = boardMapper.getPost(1L);
		boardVO.setTitle("1등입니다.");
		boardVO.setContent("하이룽");
		
		
		int result = boardMapper.update(boardVO);
		
		log.info(result);
		
		assertTrue(result == 1);
	}
	
	@Test
	public void deleteTest() {
		BoardVO boardVO= boardMapper.getPost(4L);
		
		int result = boardMapper.delete(boardVO.getBno());
		assertTrue(result == 1);
	}
}
```


라스트 인서트 아이디는 MySQL에서 제공하는 마지막으로 추가된 데이터의 id값을 받아오는 함수다.
insert와 함께 사용하면, FROM절 없이 쓸 수 있다.
라스트 인서트 아이디를 활용해서 나중에 게시물을 작성하면, 바로 사용자가 작성한 게시물을 조회하는 페이지로 이동할 수 있도록 만들 것이다.
