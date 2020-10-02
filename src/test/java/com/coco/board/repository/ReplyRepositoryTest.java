package com.coco.board.repository;

import com.coco.board.entity.Board;
import com.coco.board.entity.Member;
import com.coco.board.entity.Reply;
import com.coco.board.repositroy.BoardRepository;
import com.coco.board.repositroy.MemberRepository;
import com.coco.board.repositroy.ReplyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class ReplyRepositoryTest {

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void testGetBoardWithReply() {
        //회원 등록
        Member member = Member.builder().email("user@test.com").build();

        //게시물 등록
        Board board = Board.builder()
                .title("1번 게시물")
                .content("test")
                .writer(member)
                .build();

        //댓글 등록
        Reply reply = Reply.builder()
                .text("1번 게시물 1번 댓글이요")
                .board(board)
                .replyer("guest")
                .build();

        //DB insert
        memberRepository.save(member);
        boardRepository.save(board);
        replyRepository.save(reply);


        //댓글 등록
        Reply reply2 = Reply.builder()
                .text("1번 게시물 2번 댓글이요")
                .board(board)
                .replyer("guest2")
                .build();

        replyRepository.save(reply2);

        List<Object[]> result = replyRepository.getBoardWithReply(1L);

        for (Object[] arr : result) {
            System.out.println(Arrays.toString(arr));
        }
    }

    //조회
    @Test
    void readReply1() {
        //회원 등록
        Member member = Member.builder().email("user@test.com").build();

        //게시물 등록
        Board board = Board.builder()
                .title("1번 게시물")
                .content("test")
                .writer(member)
                .build();

        //댓글 등록
        Reply reply = Reply.builder()
                .text("1번 게시물 1번 댓글이요")
                .board(board)
                .replyer("guest")
                .build();

        //DB insert
        memberRepository.save(member);
        boardRepository.save(board);
        replyRepository.save(reply);

        Optional<Reply> result = replyRepository.findById(1L);

        Reply replyResult = result.get();

        System.out.println(replyResult);
    }

    //등록
    @Test
    void insertReply() {
        IntStream.rangeClosed(1, 300).forEach(i->{
            //1 ~ 100번 중 임의의 게시물 번호
            long bno = (long)(Math.random() * 100) + 1;

            Member member = Member.builder()
                    .email("user"+i)
                    .build();

            //게시물 생성
            Board board = Board.builder()
                    .bno((long)i)
                    .writer(member)
                    .build();


            Reply reply = Reply.builder()
                    .text("Reply..."+i)
                    .board(board)
                    .replyer("guest")
                    .build();


            memberRepository.save(member);
            boardRepository.save(board);
            replyRepository.save(reply);

        });
    }
}
