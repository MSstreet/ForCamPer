package com.examplm.camp.service;

import com.examplm.camp.dto.BoardDTO;
import com.examplm.camp.dto.PageRequestDTO;
import com.examplm.camp.dto.PageResponseDTO;

public interface BoardService {

    Long resister(BoardDTO boardDTO);

    BoardDTO readOne(Long boardId);

    void modify(BoardDTO boardDTO);

    void remove(Long boardId);

    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);
}
