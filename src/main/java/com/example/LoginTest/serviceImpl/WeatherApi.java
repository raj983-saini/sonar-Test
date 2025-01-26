package com.example.LoginTest.serviceImpl;

import com.example.LoginTest.entity.WeatherPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static com.example.LoginTest.serviceImpl.WeatherApi.api;

@Component
public class WeatherApi {
    public static  final String  apiKey= "750170a88602e778a731c44a76c41ec0";
    public static  String api = "http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

 //  resttemplete  use for call external api from the third party
 //    HttpEntity<String> httpEntity = new HttpEntity<>(
 //         "any thing like name and other "
 //    );
//    sk_dd0b6d75545dcad4a35160a3eb27acf9992636ad6570551a
    @Autowired
    public RestTemplate restTemplate;
    public  WeatherPOJO  getWeather(String city){
        String finalApi = api.replace("CITY" , city).replace("API_KEY" ,apiKey);
        // if we using the post so we give  httpentity rather than null in request entity if we give header to token so we also give httpheader
        ResponseEntity<WeatherPOJO> response = restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherPOJO.class);
        WeatherPOJO body = response.getBody();
    return  body; }

}
