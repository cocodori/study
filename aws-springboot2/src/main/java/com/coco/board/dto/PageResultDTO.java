package com.coco.board.dto;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageResultDTO <D, E> {
    private List<D> dtoList;

    private int totalPage;
    private int page;
    private int start;
    private int end;

    private List<Integer> pageList;
    private boolean prev;
    private boolean next;

    public PageResultDTO(Page<E> result, Function<E, D> fn) {
        //entity list -> dto list
        dtoList = result.stream()
                .map(fn)
                .collect(Collectors.toList());

        this.totalPage = result.getTotalPages();

        makePages(result.getPageable());
    }

    private void makePages(Pageable pageInfo) {
        this.page = pageInfo.getPageNumber() + 1;

        if(pageInfo.isPaged()) {
            pageList = new ArrayList<>();
        }

        int tempEnd = (int) (Math.ceil(page / 10.0)) * 10;

        start = tempEnd - 9;
        prev = start > 1;
        end = totalPage > tempEnd ? tempEnd : totalPage;
        next = totalPage > tempEnd;
        pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
    }
}
