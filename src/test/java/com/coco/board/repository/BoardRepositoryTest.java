package com.coco.board.repository;

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BoardRepositoryTest {

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
                    .email("user"+i)
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
            //replyRepository.save(reply);
        });
    }

    //수정
    @Test
    public void testModify() {
        Object[] entity = (Object[])boardRepository.getBoardByBno(11L);
        Board board = (Board)entity[0];
        Member member = (Member) entity[1];
        Long replyCount =(Long)entity[2];

        BoardDTO boardDto = BoardDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .regDate(board.getRegDate())
                .modDate(board.getModDate())
                .writerEmail(member.getEmail())
                .writerName(member.getName())
                .replyCount(replyCount.intValue())
                .build();

        System.out.println("dto : " + boardDto);

        boardDto.setTitle("수정 테스트");
        boardDto.setContent("수정 테스트");

        Board modBoard = Board.builder()
                .bno(boardDto.getBno())
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .writer(member)
                .build();

        boardRepository.save(modBoard);

        Object[] entity2 = (Object[])boardRepository.getBoardByBno(11L);

        System.out.println(" final board : " + entity2[0]);
    }

    @Test
    public void testRemove2() {
        System.out.println(boardRepository.getBoardByBno(11L));
        boardRepository.deleteById(11L);
        System.out.println(boardRepository.getBoardByBno(11L));
        Object[] result = (Object[])boardRepository.getBoardByBno(11L);
        System.out.println(result[0]);

        boardRepository.delete((Board)result[0]);
        System.out.println(result[0]);

    }

    /* 댓글 등록하는 코드 주석 달고 실행해야 함*/
    //삭제
    @Test
    public void testRemove() {
        System.out.println(boardRepository.getBoardByBno(11L));
        assertNotNull(boardRepository.findById(11L));

        boardRepository.deleteById(11L);

        System.out.println(boardRepository.findById(11L));

        assertEquals(boardRepository.findById(11L), Optional.empty());
    }

    //하나의 게시물 조회
    @Test
    void testRead3() {
        Object result = boardRepository.getBoardByBno(10L);

        Object[] arr = (Object[]) result;

        System.out.println(Arrays.toString(arr));
    }

    //게시물 목록 조회 (게시물, 회원, 댓글 수)
    @Test
    public void testWithReplyCount() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

        Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageable);

        result.get().forEach(row -> {
            Object[] arr = (Object[]) row;
            System.out.println(Arrays.toString(arr));
        });
    }

    @Test
    void testReadWithWriter() {
        //회원 등록
        Member member = Member.builder().email("user@test.com").build();

        //게시물 등록
        Board board = Board.builder()
                .title("1번 게시물")
                .content("test")
                .writer(member)
                .build();

        memberRepository.save(member);
        boardRepository.save(board);

        Object result = boardRepository.getBoardWithWriter(1L);

        Object[] arr = (Object[])result;

        System.out.println("-----------------------------------------------------");
        System.out.println(Arrays.toString(arr));
    }


    @Transactional
    @Test
    void testRead2() {
        //회원 등록
        Member member = Member.builder().email("user@test.com").build();

        //게시물 등록
        Board board = Board.builder()
                .title("1번 게시물")
                .content("test")
                .writer(member)
                .build();

        memberRepository.save(member);
        boardRepository.save(board);

        //1번게시물 조회
        Optional<Board> result = boardRepository.findById(1L);

        Board resultBoard = result.get();

        System.out.println(board);
        System.out.println(board.getWriter());
    }

    //조회
    @Test
    void testRead() {
        //회원 등록
        Member member = Member.builder().email("user@test.com").build();

        //게시물 등록
        Board board = Board.builder()
                .title("1번 게시물")
                .content("test")
                .writer(member)
                .build();

        memberRepository.save(member);
        boardRepository.save(board);

        //1번게시물 조회
        Optional<Board> result = boardRepository.findById(1L);

        Board resultBoard = result.get();

        System.out.println(board);
    }

    //등록
    @Test
    public void insertBoard() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder().email("user" + i + "@test.com").build();

            Board board = Board.builder()
                    .title("Title" + i )
                    .content("test")
                    .writer(member)
                    .build();

            memberRepository.save(member);
            boardRepository.save(board);
        });
    }
}
