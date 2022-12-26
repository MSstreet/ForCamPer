package com.examplm.camp.service;



import com.examplm.camp.dto.BoardDTO;
import com.examplm.camp.dto.CampSiteDto;
import com.examplm.camp.dto.PageRequestDTO;
import com.examplm.camp.dto.PageResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class BoardServiceTests {

    @Autowired
    private BoardService boardService;

    @Test
    public void testRegister(){
        log.info(boardService.getClass().getName());

        BoardDTO BoardDTO = com.examplm.camp.dto.BoardDTO.builder().title("Sample Title...").content("Sample Content").writer("user00").build();

        Long bnum = boardService.resister(BoardDTO);

        log.info("bnum: " + bnum);
    }

    @Test
    public void testModify(){

        BoardDTO boardDTO = com.examplm.camp.dto.BoardDTO.builder().boardId(101L).title("Updated...101").content("Updated content 101...").build();

        boardService.modify(boardDTO);
    }

    @Test
    public void testList(){

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .type("tcw")
                .keyword("1")
                .page(1)
                .size(10)
                .build();

        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);

        log.info(responseDTO);
    }

}
