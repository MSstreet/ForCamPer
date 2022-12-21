package com.examplm.camp.dao.serch;

import com.examplm.camp.domain.CampSite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CampSearch {

    Page<CampSite> search1(Pageable pageable);

    Page<CampSite> searchAll(String[] types, String keyword, Pageable pageable);
}
