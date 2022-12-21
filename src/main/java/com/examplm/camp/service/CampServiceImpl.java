package com.examplm.camp.service;

import com.examplm.camp.dao.CampRepository;
import com.examplm.camp.domain.CampSite;
import com.examplm.camp.dto.CampSiteDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JsonParser;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class CampServiceImpl implements CampService{

    @Value("${camp.serviceKey}")
    private String serviceKey;

    private final CampRepository campRepository;

    @Override
    public void SaveCampingSites() {

        StringBuffer result = new StringBuffer();
        String jsonPrintString = null;

        try {
            String apiUrl = "http://apis.data.go.kr/B551011/GoCamping/basedList?" +
                    "serviceKey=" + this.serviceKey +
                    "&numOfRows=10" + //need to add
                    //"&pageNo=1" +
                    "&MobileOS=ETC" +
                    "&MobileApp=TestApp" +
                    "&_type=json";

            URL url = new URL(apiUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream, "UTF-8"));
            String returnLine;

            while ((returnLine = bufferedReader.readLine()) != null) {
                result.append(returnLine);
            }

            //System.out.println("response body : " + result);

            JSONObject obj = new JSONObject(result.toString());
            JSONObject jResponse = obj.getJSONObject("response");
            JSONObject jBody = jResponse.getJSONObject("body");
            JSONObject jitems = jBody.getJSONObject("items");
            JSONArray jitem = jitems.getJSONArray("item");

            for(int i = 0; i < jitem.length(); i++) {
                JSONObject jobj = jitem.getJSONObject(i);
                CampSite campSite = CampSite.builder()
                        .facltNm(jobj.getString("facltNm"))
                        .intro(jobj.getString("intro"))
                        .lineIntro(jobj.getString("lineIntro"))
                        .firstImageUrl(jobj.getString("firstImageUrl"))
                        .animalCmgCl(jobj.getString("animalCmgCl"))
                        .indvdlCaravSiteCo(jobj.getString("indvdlCaravSiteCo"))
                        .caravSiteCo(jobj.getString("caravSiteCo"))
                        .glampSiteCo(jobj.getString("glampSiteCo"))
                        .autoSiteCo(jobj.getString("autoSiteCo"))
                        .gnrlSiteCo(jobj.getString("gnrlSiteCo"))
                        .manageNmpr(jobj.getString("manageNmpr"))
                        .resveUrl(jobj.getString("resveUrl"))
                        .homepage(jobj.getString("homepage"))
                        .tel(jobj.getString("tel"))
                        .direction(jobj.getString("direction"))
                        .mapX(jobj.getString("mapX"))
                        .mapY(jobj.getString("mapY"))
                        .zipcode(jobj.getString("zipcode"))
                        .addr1(jobj.getString("addr1"))
                        .addr2(jobj.getString("addr2"))
                        .doNm(jobj.getString("doNm"))
                        .sigunguNm(jobj.getString("sigunguNm"))
                        .featureNm(jobj.getString("featureNm"))
                        .toiletCo(jobj.getString("toiletCo"))
                        .swrmCo(jobj.getString("swrmCo"))
                        .sbrsCl(jobj.getString("sbrsCl"))
                        .sbrsEtc(jobj.getString("sbrsEtc"))
                        .manageSttus(jobj.getString("manageSttus"))
                        .build();

                campRepository.save(campSite);

            }

            //System.out.println(jitem);


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
