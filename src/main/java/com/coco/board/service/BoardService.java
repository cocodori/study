package com.coco.board.service;

import com.coco.board.dto.BoardDTO;
import com.coco.board.dto.PageRequestDTO;
import com.coco.board.dto.PageResultDTO;
import com.coco.board.entity.Board;
import com.coco.board.entity.Member;

import java.util.Optional;

public interface BoardService {

    Long register(BoardDTO boardDto);
    PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO);
    BoardDTO getPost(Long bno);
    void removePost(Long bno);

    default Board dtoToEntity(BoardDTO boardDto) {
        Member member = Member.builder()
                .email(boardDto.getWriterEmail())
                .build();

        Board board = Board.builder()
                .bno(boardDto.getBno())
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .writer(member)
                .build();

        return board;
    }

    default BoardDTO entityToDto(Board board, Member member, Long replyCount) {
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .regDate(board.getRegDate())
                .modDate(board.getModDate())
                .writerEmail(member.getEmail())
                .writerName(member.getName())
                .replyCount(replyCount.intValue())
                .build();

        return boardDTO;
    }
}
