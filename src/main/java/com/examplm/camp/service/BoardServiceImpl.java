package com.examplm.camp.service;

import com.examplm.camp.dao.BoardRepository;
import com.examplm.camp.domain.Board;
import com.examplm.camp.domain.CampSite;
import com.examplm.camp.dto.BoardDTO;
import com.examplm.camp.dto.CampSiteDto;
import com.examplm.camp.dto.PageRequestDTO;
import com.examplm.camp.dto.PageResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public  BoardDTO readOne(Long boardId){

        Optional<Board> result = boardRepository.findById(boardId);

        Board board = result.orElseThrow();

        BoardDTO reviewBoardDTO = modelMapper.map(board,BoardDTO.class);

        return reviewBoardDTO;
    }

    @Override
    public void modify(BoardDTO boardDTO){

        Optional<Board> result = boardRepository.findById(boardDTO.getBoardId());

        Board board = result.orElseThrow();

        board.change(boardDTO.getTitle(), boardDTO.getContent());

        boardRepository.save(board);
    }

    @Override
    public void remove(Long boardId){
        boardRepository.deleteById(boardId);
    }


    @Override
    public PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO){
        String[] types = pageRequestDTO.getType();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable();

        Page<Board> result = boardRepository.searchAll1(types, keyword, pageable);

        List<BoardDTO> dtoList = result.getContent().stream().map(board -> modelMapper.map(board,
                BoardDTO.class)).collect(Collectors.toList());

        return PageResponseDTO.<BoardDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }

}
