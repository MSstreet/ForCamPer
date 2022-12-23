package com.examplm.camp.controller;

import com.examplm.camp.dto.CampSiteDto;
import com.examplm.camp.dto.PageRequestDTO;
import com.examplm.camp.dto.PageResponseDTO;
import com.examplm.camp.service.CampService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/camping")
@RequiredArgsConstructor
public class CampController {

    private final CampService campService;

    @GetMapping("/jsonapi")
    public void saveCampingSitesFromAPI() {
        campService.SaveCampingSites();
    }

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){
        PageResponseDTO<CampSiteDto> responseDTO = campService.list(pageRequestDTO);

        model.addAttribute("responseDTO", responseDTO);

        //return "camping/list";
    }

}
