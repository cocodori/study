# Servlet/JSP 답변형 게시판 구현하기
## 🛠 Stack
- Servlet/JSP
- MySQL
- vanilla Javascript

> 참고 : 자바 웹을 다루는 기술

servlet/jsp를 이용해서 게시판을 어떻게 만드나, 스프링과는 어떻게 다른가 궁금해서 만들어보았다.
간단하게 crud만 구현해볼 목적이었으므로 레이아웃은 크게 신경쓰지 않았다.
- 우선 가장 불편했던 점은 자동 수집이었다. VO객체를 직접 수집하지 못하니 하나하나 일일이 request.getParameter로 받아서 직접 vo를 생성해야 하니... 몹시 귀찮은 작업이었다.

- MyBatis 같은 프레임워크를 이용하지 못해서 하나하나 DataSource로 커넥션을 만들고... PreparedStatement로 값을 지정하고, ResultSet으로 결과를 받고 하는 일이 몹시 귀찮았다.

- 이건 참고 서적의 문제일지도 모르겠다. get요청과 post요청 모두 doHandle()를 만들어서 한 번에 처리한다.
이때 생기는 문제점은 반드시 post로만 접근해야 하는 url에 get방식으로도 접근할 수 있게 된다는 것이다.
@GetMapping이나 @PostMapping 같은 애노테이션을 사용할 수 있었으면 안정성 면에서도 좋았을 듯하다.

- 그리고 영속 계층에서만 테스트를 진행할 수밖에 없었던 것은 좀 아쉬웠다. 능력 부족. 이것 또한 스프링에서는 아무 문제 없이 진행할 수 있었다.



> 보다 자세한 내용은 블로그에 정리해두었습니다. [✈이동](https://velog.io/@cocodori/series/ServletJSP-%EB%8B%B5%EB%B3%80%ED%98%95-%EA%B2%8C%EC%8B%9C%ED%8C%90-%EB%A7%8C%EB%93%A4%EA%B8%B0)

# 게시물 목록(/board/list)

![](https://images.velog.io/images/cocodori/post/7e1219af-b7a7-4556-86ea-ddd44f9bda5a/image.png)

# 게시물 등록 (/board/write)

![](https://images.velog.io/images/cocodori/post/09f9eb65-5a31-4650-b8bf-aad57a4b9ff7/image.png)

![](https://images.velog.io/images/cocodori/post/72c7e1e4-d208-4226-b304-c22f71b3df7d/image.png)

# 게시물 조회 (/board/post)

![](https://images.velog.io/images/cocodori/post/538d4876-fcbd-4078-bc5b-86e28ae1f9c3/image.png)

# 게시물 수정 (/board/post - /board/modify)

>수정하는 페이지를 따로 만들지 않고, 자바스크립트를 이용하여 조회페이지에서 바로 수정할 수 있도록 했다.

![](https://images.velog.io/images/cocodori/post/a2fd366d-e2c6-4f1e-b712-39240688b825/1.png)

![](https://images.velog.io/images/cocodori/post/09a9bba7-0443-460c-812d-c6ac54a23b72/2.png)

![](https://images.velog.io/images/cocodori/post/bfb53c9d-3410-4acd-ae44-7bb07f7c6cc1/3.png)

# 게시물 삭제 (/board/remove)

![](https://images.velog.io/images/cocodori/post/00c51196-d064-480a-8531-73e79b9eb32b/image.png)

![](https://images.velog.io/images/cocodori/post/4b0ca480-e538-4497-af3c-3e8ba919e701/image.png)

![](https://images.velog.io/images/cocodori/post/68940aa5-0a6e-41ba-972d-b3f03c8c0246/image.png)

alert사용은 모든 작업을 중지하기 때문에 권장되지 않는다. 모달창을 띄우는 게 더 나은 방법일 것이다.

# ❓
# 🤯 게시글 삭제할 때 고민했던 점


- 삭제 했을 때 
    - 원글만 지운다.

        원글은 '삭제된 글입니다.'라고 띄우고 답글은 살려두는 형태

    - 답글까지 지운다.

        원글을 지우면 답글까지 지워지는 경우.

    페이스북의 경우는 답글까지 지우는 형태다. 반면 네이버는 답글은 두고 원글만 지운다.
    뭐가 더 좋은 방법일까? 상황에 따라 다를 것이다.
    
     나는 1번 원글만 지우는 방식을 선택했다. 댓글의 권한은 댓글을 쓴 사람에게 있다고 보기 때문이다.
     

## 어떻게 ?

	처음 생각한 방식은 쿼리를 두 번 날리는 것이다.
    
    1. 카운트 쿼리를 날린다.
    
    2. 이 결과가 0보다 크다면, 답글이 존재하는 것이므로,
       delete가 아닌, 기존의 데이터를 없애고 '삭제된 게시물입니다.' 라는 문구로 update 한다.
    
    3. 결과가 0이라면 답글이 없다는 것이다.
       따라서 그냥 delete 쿼리를 날린다.

이 방식의 단점은 여러 번 쿼리를 날린다는 것이다.

DB의 프로시저Procedure를 이용하면 IF-ELSE 처리를 할 수 있다는 것을 알게 되었다.
**MySQL**
```sql
-- 삭제한 게시물에 자식 노드(답변글)이 있는지 확인하는 프로시저
DELIMITER $$
DROP PROCEDURE IF EXISTS deletePost$$
CREATE PROCEDURE deletePost(IN bno int(10))
 BEGIN	
 	SET @bno = bno;
    SET @count = (SELECT COUNT(*) FROM t_board t
					WHERE t.p_bno = @bno);
    
    IF @count > 0 THEN
        UPDATE t_board t
        SET  t.title = '삭제된 게시글입니다.'
	    ,t.content = ''
            ,t.id = ''
        WHERE t.bno = @bno;   
        
    ELSE 
	DELETE FROM t_board t
        WHERE t.bno = @bno;
    END IF;
END $$
DELIMITER 
```
 위 쿼리는 bno를 받아서 답변글이 있는지 확인하고 있다면 업데이트를, 없다면 삭제하는 프로시저다.
 이렇게 정의해두면, 나중에는
 ```sql
 -- CALL deletePost(bno번호);
 CALL deletePost(45);
 ```
 이렇게 호출해서 사용할 수 있었다. 일종의... 메서드 같았다.
  
 ![](https://images.velog.io/images/cocodori/post/5846b9c7-9196-480a-9d13-67c18475885e/image.png)
 <center>WorkBench</center>

 ![](https://images.velog.io/images/cocodori/post/d2e701a5-7322-4517-87ef-5f45ea8618a1/image.png)
 <center>View</center>

# 변경된 코드

화면, 컨트롤러, 서비스 계층에는 변화가 없다.
DB와 바로 연결되는 DAO에서도 변경하는 부분은 sql쿼리 한 줄 뿐이다.

## DAO
### 테스트
```java
    @Test
    public void deleteProcedureTest() throws ClassNotFoundException {
    	Class.forName(DRIVER);
    	String sql = "CALL deletePost(?)";
    	log.info(sql);
    	try(
            Connection conn = DriverManager.getConnection(URL,USER,PW);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            	) {
    		
    		pstmt.setInt(1, 26);
    		
    		int result = pstmt.executeUpdate();
    		
    		assertTrue(result == 1);
    		
    	} catch (Exception e) {
    		log.info(e.getMessage());
    	}
    }
```

### 프로젝트
```java
	public int delete(int bno) {
		String sql = "CALL deletePost(?)";
    	log.info(sql);
    	try(
            Connection conn = ds.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ) {
        		
        	pstmt.setInt(1, bno);
        		
        	return pstmt.executeUpdate();
        		
    	} catch (Exception e) {
    		log.info(e.getMessage());
        }
		return -1;
	}
```

최소한의 변화다!
만약 첫 번째 방법으로 했다면 이보다는 더 많이 변경해야 했을 것이다.

### post.jsp

삭제된 게시물입니다'로 업데이트 후에, 화면처리. 화면에서 보이는 버튼 중에 '목록'만 남기고 '수정/삭제/답글'은 지워야 한다. post.jsp에 다음과 같은 부분을 추가했다.

```javascript
const id = document.getElementById('id')

//id의 길이가 0이거나 ''일 때, 수정/삭제/답글 버튼을 보이지 않게 한다.
if(id.value.length==0||id==='') {
	document.getElementById('modify').style.display='none'		
	document.getElementById('rePost').style.display='none'		
	document.getElementById('remove').style.display='none'		
}
```

![](https://images.velog.io/images/cocodori/post/7c8cf7b0-4b9f-423a-b52b-990b5e0f42b1/image.png)
<center>다른 버튼들이 사라진 것을 확인할 수 있다</center>


### 그 외 문제점
- ~~'삭제된 게시물입니다'로 업데이트 후에, 화면처리. 화면에서 보이는 버튼 중에 '목록'만 남기고 '수정/삭제/답글'은 지워야 한다. 해결~~ 

 - 게시물 삭제 → 새로 고침 시 계속해서 alert창이 뜨는 이슈
    
 - 계층구조 때문에 DB와 화면의 게시물 번호가 다르다는 점.
 	그리고 역순 정렬을 할 수 없다는 것. 
	
	
	<hr>

# 답변 등록하기
답글은 간단하다. 따로 DAO를 만들 필요가 없다. register메서드를 그대로 이용하면 된다.

1. post.jsp에서 '답글' 버튼을 누른다.
2. 해당 post의 번호(bno)를 가지고 rePost.jsp로 이동한다.
3. 작성한 답글의 데이터로 VO를 만들어서 register메서드를 호출한다.
4. 끝

재밌는 점은 register와 등록 방식이 완전히 동일하기 때문에 새로운 post메서드를 만들지 않아도 된다는 것이다.
register메서드에 단순히 조건을 하나 추가해주기만 하면 된다! 이른바 재사용성!

![](https://images.velog.io/images/cocodori/post/71bbb90d-51c1-4db7-adbd-ffd5f0699a54/image.png)

# 페이징
- 첫 페이지와 마지막 페이지
![](https://images.velog.io/images/cocodori/post/66a82df8-aaa2-4c16-b5ea-1098477a7c05/image.png)

![](https://images.velog.io/images/cocodori/post/dfb2ea9f-89ce-4bbe-bf44-f8b0d0f55941/image.png)

