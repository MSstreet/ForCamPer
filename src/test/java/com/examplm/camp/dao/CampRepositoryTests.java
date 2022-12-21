package com.examplm.camp.dao;

import com.examplm.camp.domain.CampSite;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@SpringBootTest
@Log4j2
public class CampRepositoryTests {

    @Autowired
    private CampRepository campRepository;

    @Test
    public void testSearch1(){
        Pageable pageable = PageRequest.of(1,10, Sort.by("CampNum").descending());

        campRepository.search1(pageable);
    }

    @Test
    public void testSearchAll(){

        String[] types = {"n", "a", "s"};

        String keyword = "1";

        Pageable pageable = PageRequest.of(0, 10, Sort.by("campNum").descending());

        Page<CampSite> result = campRepository.searchAll(types, keyword, pageable);
    }

    @Test
    public void testSearchAll2(){


        String[] types = {"n", "a", "s"};

        String keyword = "1";

        Pageable pageable = PageRequest.of(0, 10, Sort.by("campNum").descending());

        Page<CampSite> result = campRepository.searchAll(types, keyword, pageable);

        log.info(result.getTotalPages());

        log.info(result.getSize());

        log.info(result.getNumber());

        log.info(result.hasPrevious() +": " + result.hasNext());

        result.getContent().forEach(campSite -> log.info(campSite));
    }

}
