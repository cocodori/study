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
				.title("Mapper Insert Test")
				.content("Hello MyBatis Mapper")
				.writer("user0")
				.build();
		
		int result = boardMapper.insert(boardVO);
		
		log.info("result : " + result);
		
		assertTrue(result == 1);
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