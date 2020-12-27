# Presentation Layer

컨트롤러와 화면처리를 담당할 jsp가 프레젠테이션 계층이다. 

컨트롤러를 완성하고 화면처리를 할 것이다.

URL분기는 [참고](https://velog.io/@cocodori/Spring-%EA%B2%8C%EC%8B%9C%ED%8C%90-%EA%B5%AC%ED%98%84)

달라진 점은 수정 페이지를 따로 만들지 않고 자바스크립트를 이용해서 처리할 것이므로 따로 분기하지 않았다.

## 컨트롤러

```java
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
@Log4j
public class BoardController {
	
	private final BoardService boardService;
	
	@GetMapping("/list")
	public void getAllPost(Model model) {
		
		log.info("/board/list");
		
		model.addAttribute("list", boardService.getAllPost());
	}
	
	@GetMapping("/write")
	public void write() {
		log.info("/board/write");
	}
	
	@PostMapping("/register")
	public String register(BoardVO boardVO, RedirectAttributes redirect) {
		log.info("/board/register");
		log.info("boardVO : "  + boardVO);

		redirect.addAttribute("no", boardService.register(boardVO));
		
		return "redirect:/board/post";
	}
	
	@GetMapping("/post")
	public void getPost(Long no, Model model) {
		log.info("/board/post");
		
		model.addAttribute("post", boardService.getPost(no));
	}
	
	
	@PostMapping("/modify")
	public String modify(BoardVO boardVO, RedirectAttributes redirect) {
		log.info("/board/modify");
		log.info("BoardVO : " + boardVO);
		
		int result = boardService.modify(boardVO);
		
		log.info("MODIFY RESULT : " + result);
		
		redirect.addAttribute("no",boardVO.getBno());
		
		return "redirect:/board/post";
	}

	@PostMapping("/remove")
	public String remove(Long bno) {
		log.info("/board/remove");
		log.info("bno : " + bno);
		
		int result = boardService.remove(bno);
		log.info("result : " + result);
		
		return "redirect:/board/list";
	}
}
```

## MockMvc Test

컨트롤러는 화면과 연결하기 때문에 결과를 바로 화면에서 확인할 수 있다.
그러나
스프링은 서버를 구동하는 데 약간의 시간이 걸리므로, MockMvc객체로 가상 MVC환경을 구성해서 테스트 해보는 것이 좋다.


```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class, ServletConfig.class})
@Log4j
//ServletContext의 WebApplicationContext를 이용하기 위해서 선언
@WebAppConfiguration
public class BoardControllerTest {
	
	@Autowired
	private WebApplicationContext webContext;
	
	private MockMvc mockMvc;
	
	//테스트를 실행하기 전에 매번 실행한다.
	@Before
	public void setup() {	//MockMvc객체를 초기화 하는 메서드
		this.mockMvc = MockMvcBuilders
				.webAppContextSetup(webContext)
				.build();
	}

	
	@Test
	public void getAllPostTest() {
		ModelMap result;
		
		try {
			 result = mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
				.andReturn()
				.getModelAndView()
				.getModelMap();
			
			assertNotNull(result);
			log.info(result);
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void registerTest() {
		try {
			String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/register")
					.param("title", "mockMvcTest")
					.param("content", "volvoKO")
					.param("writer", "Elec")
					).andReturn().getModelAndView().getViewName();
			
			assertTrue(resultPage.length()!=0||!resultPage.equals("")||resultPage!=null);
			log.info("resultPage : " + resultPage);
			
		} catch (Exception e) {
			fail(e.getMessage());
			log.info(e.getMessage());
		}
	}
	
	@Test
	public void getPostTest() {
		try {
			ModelMap result = mockMvc.perform(MockMvcRequestBuilders
					.get("/board/post")
					.param("no", "1"))
			.andReturn()
			.getModelAndView()
			.getModelMap();
			
			assertNotNull(result);
			log.info(result);
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testModify() {
		try {
			String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/modify")
					.param("bno", "1")
					.param("title", "MockMVC MODIFYING")
					.param("content", "HELLO, MOCK MVC?"))
			.andReturn()
			.getModelAndView()
			.getViewName();
			
			assertTrue(resultPage != null 
						|| !resultPage.equals("")
						|| resultPage.length() != 0);
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testRemove() {
		try {
			String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/remove")
					.param("bno", "253"))
			.andReturn()
			.getModelAndView()
			.getViewName();
			
			assertNotNull(resultPage);
			
			log.info("resultPage : " + resultPage);
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}

```

여기까지가 컨트롤러 부분의 끝이다. 물론 이것은 버전1이다.
이만큼만 완성하고 화면과 붙여서 제대로 작동하는지 확인한다.
그 다음 페이징, 검색, 파일 업로드 같은 기능을 추가하는 식으로 개발한다.

