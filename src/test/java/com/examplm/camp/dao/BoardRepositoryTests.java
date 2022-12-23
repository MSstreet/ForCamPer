package com.examplm.camp.dao;

import com.examplm.camp.domain.Board;
import com.examplm.camp.domain.CampSite;
import com.examplm.camp.domain.User;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testInsert(){


        IntStream.rangeClosed(1,100).forEach(i -> {



            Board board = Board.builder()
                    .writer("user..." + i)
                    .title("title..." + i)
                    .content("content..."+i)
                    .deleteYn(false)
                    .build();

            Board result = boardRepository.save(board);
            log.info("BNUM: " + result.getBoardId());
        });
    }

    @Test
    public void testSearch1(){
        Pageable pageable = PageRequest.of(1,10, Sort.by("boardId").descending());

        boardRepository.search2(pageable);
    }

    @Test
    public void testSearchAll(){

        String[] types = {"t", "c", "w"};

        String keyword = "1";

        Pageable pageable = PageRequest.of(0, 10, Sort.by("boardId").descending());

        Page<Board> result = boardRepository.searchAll1(types, keyword, pageable);
    }
}
