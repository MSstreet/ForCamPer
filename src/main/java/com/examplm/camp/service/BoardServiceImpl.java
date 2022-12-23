package com.examplm.camp.service;

import com.examplm.camp.dao.BoardRepository;
import com.examplm.camp.domain.Board;
import com.examplm.camp.dto.BoardDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService{

    private final ModelMapper modelMapper;

    private final BoardRepository boardRepository;

    @Override
    public Long resister(BoardDTO boardDTO){
        Board board = modelMapper.map(boardDTO, Board.class);

        Long boardId = boardRepository.save(board).getBoardId();

        return boardId;
    }
}
