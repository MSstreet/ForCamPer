package com.examplm.camp.service;

import com.examplm.camp.dto.CampSiteDto;
import com.examplm.camp.dto.PageRequestDTO;
import com.examplm.camp.dto.PageResponseDTO;

public interface CampService {

    void SaveCampingSites();

    CampSiteDto readOne(Long cmapNum);

    PageResponseDTO<CampSiteDto> list(PageRequestDTO pageRequestDTO);
}
