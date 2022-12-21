package com.examplm.camp.service;

import com.examplm.camp.dto.CampSiteDto;
import com.examplm.camp.dto.PageRequestDTO;
import com.examplm.camp.dto.PageResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class CampServiceTest {

    @Autowired
    private CampService campService;

    @Test
    public void testList(){

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .type("nas")
                .keyword("1")
                .page(1)
                .size(10)
                .build();

        PageResponseDTO<CampSiteDto> responseDTO = campService.list(pageRequestDTO);

        log.info(responseDTO);
    }
}
