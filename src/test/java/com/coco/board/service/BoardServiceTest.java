package com.coco.board.service;

import com.coco.board.dto.BoardDTO;
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
            replyRepository.save(reply);
        });
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
}
