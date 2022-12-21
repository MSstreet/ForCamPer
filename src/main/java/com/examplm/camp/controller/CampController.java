package com.examplm.camp.controller;

import com.examplm.camp.service.CampService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/camp")
@RequiredArgsConstructor
public class CampController {

    private final CampService campService;

    @GetMapping("/jsonapi")
    public void saveCampingSitesFromAPI() {

        campService.SaveCampingSites();

    }

}
