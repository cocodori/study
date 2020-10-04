package com.coco.board.service;

import com.coco.board.dto.BoardDTO;
import com.coco.board.dto.PageRequestDTO;
import com.coco.board.dto.PageResultDTO;
import com.coco.board.entity.Board;
import com.coco.board.entity.Member;
import com.coco.board.repositroy.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

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

    @Override
    public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {

        log.info(pageRequestDTO);

        Function<Object[], BoardDTO> fn = (en -> entityToDto((Board)en[0],(Member)en[1],(Long)en[2]));

        Page<Object[]> result = boardRepository.getBoardWithReplyCount(
                pageRequestDTO.getPageable(Sort.by("bno").descending()));


        return new PageResultDTO<>(result, fn);
    }

    @Override
    public Object getPost(Long bno) {
        return boardRepository.getBoardByBno(bno);
    }
}
