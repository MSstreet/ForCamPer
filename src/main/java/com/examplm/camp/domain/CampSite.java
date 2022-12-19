package com.examplm.camp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CampSite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CampNum;

    private String facltNm;

    private String intro;

    private String lineIntro;

    private String firstImageUrl;

    private String animalCmgCl;


    private String indvdlCaravSiteCo;

    private String caravSiteCo;

    private String glampSiteCo;

    private String autoSiteCo;

    private String gnrlSiteCo;

    private String manageNmpr;

    private String resveUrl;

    private String homepage;

    private String tel;

    private String direction;

    private String mapX;

    private String mapY;

    private String zipcode;

    private String addr1;

    private String addr2;

    private String doNm;

    private String sigunguNm;

    private String featureNm;

    private String toiletCo;

    private String swrmCo;

    private String sbrsCl;

    private String sbrsEtc;

    private String manageSttus;
}
