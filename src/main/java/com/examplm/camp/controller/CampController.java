package com.examplm.camp.controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@RequestMapping("/camp")
public class CampController {


    @GetMapping("/jsonapi")
    public String callApiWithJson() {
        StringBuffer result = new StringBuffer();

        String jsonPrintString = null;

        try {
            String apiUrl = "http://apis.data.go.kr/B551011/GoCamping/basedList?" +
                    "serviceKey=qXgXNCnN8YyFCU9K2spQGBqadfslQnEX88quXQctvs85q3Gv%2Bb6g1zXD9sKBQS9%2FVg5iLR%2FEl7BJ0saDWOdLmw%3D%3D" +
                    "&numOfRows=10" +
                    "&pageNo=1" +
                    "&MobileOS=ETC" +
                    "&MobileApp=TestApp"+
                    "&_type=json";

            URL url = new URL(apiUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.connect();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream, "UTF-8"));

            String returnLine;

            while((returnLine = bufferedReader.readLine()) != null) {
                result.append(returnLine);
            }
            System.out.println("response body : " + result);

           JSONObject obj = new JSONObject(result.toString());
           JSONObject jResponse = obj.getJSONObject("response");
           JSONObject jBody = jResponse.getJSONObject("body");
           JSONObject jitems = jBody.getJSONObject("items");
            JSONArray jitem = jitems.getJSONArray("item");

            for(int i = 0; i < jitem.length(); i++){
                JSONObject obj1 = jitem.getJSONObject(i);
                String name = obj1.getString("facltNm");
                System.out.println(name);
            }

            //JSONObject jObject = new JSONObject(result);
            //JSONArray jResponse = jObject.getJSONArray("response");
            //JSONObject jHeader = jResponse.getJSONObject("header");
            //JSONArray jHeader = jResponse.getJSONArray(0);

            System.out.println(jitem);


            //JSONObject jsonObject = XML.toJSONObject(result.toString());
            jsonPrintString = result.toString();


        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonPrintString;
    }

}
