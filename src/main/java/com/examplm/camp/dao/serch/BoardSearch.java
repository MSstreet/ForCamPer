package com.examplm.camp.dao.serch;

import com.examplm.camp.domain.Board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardSearch {

    Page<Board> search2(Pageable pageable);


    Page<Board> searchAll1(String[] types, String keyword, Pageable pageable);
}
