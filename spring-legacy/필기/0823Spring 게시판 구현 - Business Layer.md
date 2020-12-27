# Business Layer

비지니스 계층은 'Service'라는 이름을 주로 사용한다.

BoardService라는 인터페이스를 만들고
BoardServiceImpl클래스가 구현하는 형태로 만들 것이다.

굳이 인터페이스를 중간에 두는 이유는 다음에 구현할 프레젠테이션 계층과 강결합을 피하기 위해서다. 

## service패키지 생성
![](https://images.velog.io/images/cocodori/post/b5c6acb5-1533-4768-870e-2228bd100101/image.png)

```java
public interface BoardService {

	Long register(BoardVO boardVO);
	List<BoardVO> getAllPost();
	BoardVO getPost(Long bno);
	int modify(BoardVO boardVO);
	int remove(Long bno);
	
}
```

```java
@Service
@RequiredArgsConstructor
@Log4j
public class BoardServiceImpl implements BoardService {

	private final BoardMapper boardMapper;
	
	@Override
	public Long register(BoardVO boardVO) {
		/* 데이터베이스에 데이터를 추가하는 데 성공하면 
		 * 추가된 데이터의 bno를 반환하고, 실패하면 0을 반환한다.
		 * */
		Long result =
				boardMapper.insert(boardVO) == 1 ?
						boardMapper.lastInsertId() : 0;
		
		return result;
	}

	@Override
	public List<BoardVO> getAllPost() {
		return boardMapper.getAllPost();
	}

	@Override
	public BoardVO getPost(Long bno) {
		return boardMapper.getPost(bno);
	}

	@Override
	public int modify(BoardVO boardVO) {
		return boardMapper.update(boardVO);
	}

	@Override
	public int remove(Long bno) {
		
		int result = boardMapper.delete(bno);
		log.info(result == 0 ? "존재하지 않는 게시물" : "");
		
		return result;
	}
}
```

```@RequiredArgsConstructor```를 이용해서 묵시적 자동 주입을 사용했다.


## RootConfig 추가
```
@ComponentScan(basePackages = {"com.coco.controller", "com.coco.service"})
```

## Test
```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
@Log4j
public class BoardServiceTest {

	@Autowired
	BoardService boardService;
	
	@Test
	public void serviceIsExistTest() {
		assertNotNull(boardService);
		log.info(boardService);
	}
	
	@Test
	public void registerTest() {
		BoardVO boardVO = BoardVO.builder()
				.title("BUSINESS LAYER REGISTER TEST")
				.content("HELLO, BUSINESS?")
				.writer("angryBird")
				.build();
		
		//성공하면 last insert id를 반환
		//실패하면 0을 반환
		Long result = boardService.register(boardVO);
		
		assertTrue(result>0);
		log.info(result);
	}
	
	@Test
	public void getAllPostTest() {
		List<BoardVO> allPostList = boardService.getAllPost();
		
		assertNotNull(allPostList);
		
		allPostList.forEach(element -> log.info(element));
	}
	
	@Test
	public void getPostTest() {
		BoardVO boardVO = boardService.getPost(15L);
		assertNotNull(boardVO);
		
		log.info(boardVO);
	}
	
	@Test
	public void modifyTest() {
		BoardVO boardVO = boardService.getPost(15L);
		
		boardVO.setTitle("BUSINESS LAYER MODIFY TEST");
		boardVO.setContent("이럴 거면 왜 같이 온 거야?");
		boardVO.setWriter("impo");
		
		int result = boardService.modify(boardVO);
		
		assertTrue(result == 1);
	}
	
	@Test
	public void removeTest() {
		
		int result = boardService.remove(217L);
		assertTrue(result == 1);
	}
}
```

