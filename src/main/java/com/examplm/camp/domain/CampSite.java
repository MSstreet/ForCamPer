package com.examplm.camp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class CampSite extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long campNum;

    @Column(length = 500)
    private String facltNm;

    @Column(length = 3500)
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
    @Column(length = 500)
    private String resveUrl;

    private String homepage;

    private String tel;

    @Column(length = 1000)
    private String direction;

    private String mapX;

    private String mapY;

    private String zipcode;

    private String addr1;

    private String addr2;

    private String doNm;

    private String sigunguNm;

    @Column(length = 500)
    private String featureNm;

    private String toiletCo;

    private String swrmCo;

    private String sbrsCl;

    private String sbrsEtc;

    private String manageSttus;
}
