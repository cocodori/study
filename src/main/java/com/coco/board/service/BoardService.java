package com.coco.board.service;

import com.coco.board.dto.BoardDTO;
import com.coco.board.entity.Board;
import com.coco.board.entity.Member;

public interface BoardService {

    Long register(BoardDTO boardDto);

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
}
