package com.coco.board.service;

import com.coco.board.dto.BoardDTO;
import com.coco.board.dto.PageRequestDTO;
import com.coco.board.dto.PageResultDTO;
import com.coco.board.entity.Board;
import com.coco.board.entity.Member;
import com.coco.board.entity.Reply;
import com.coco.board.repositroy.BoardRepository;
import com.coco.board.repositroy.MemberRepository;
import com.coco.board.repositroy.ReplyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ReplyRepository replyRepository;
    @BeforeEach
    void beforeStart() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder()
                    .email("user"+i+"@test.com")
                    .name("anonny")
                    .build();

            Board board = Board.builder()
                    .title("TITLE " + i)
                    .content(i+"")
                    .writer(member)
                    .build();

            Reply reply = Reply.builder()
                    .board(board)
                    .replyer("user"+i)
                    .build();

            memberRepository.save(member);
            boardRepository.save(board);
           // replyRepository.save(reply);
        });
    }

    @Test
    public void testModify() {
        BoardDTO boardDto = boardService.getPost(11L);
        System.out.println(boardDto);

        boardDto.setTitle("서비스 수정 테스트");
        boardDto.setContent("Service Layer");
        boardService.modify(boardDto);

        System.out.println(boardService.getPost(11L));
    }

    /* 댓글 등록하는 코드 주석 달고 실행해야 함*/
    @Test
    public void testRemove() {
        assertNotNull(boardService.getPost(99L));
        boardService.removePost(99L);
        System.out.println(boardService.getList(new PageRequestDTO()));

        //삭제가 되었고.. getList()로 조회하면 삭제 된 것을 확인할 수 있는데
        //getPost()로는 여전히 조회가 된다..?

        System.out.println(boardService.getPost(99L));

    }


    @Test
    public void testRead2() {
        System.out.println(boardService.getPost(11L));
    }

    @Test
    void testRegister() {
        BoardDTO boardDto = BoardDTO.builder()
                .title("test1")
                .content("cont")
                .writerEmail("user55@test.com")
                .build();

        Long bno = boardService.register(boardDto);
    }

    @Test
    void testList() {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        PageResultDTO<BoardDTO, Object[]> result = boardService.getList(pageRequestDTO);

        for(BoardDTO boardDto : result.getDtoList()) {
            System.out.println(boardDto);
        }
    }
}
