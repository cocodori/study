package com.coco.board.service;

import com.coco.board.dto.BoardDTO;
import com.coco.board.entity.Board;
import com.coco.board.repositroy.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;

    @Override
    public Long register(BoardDTO boardDto) {

        log.info("boardDto : " + boardDto);

        Board board = dtoToEntity(boardDto);

        boardRepository.save(board);

        return board.getBno();
    }
}
