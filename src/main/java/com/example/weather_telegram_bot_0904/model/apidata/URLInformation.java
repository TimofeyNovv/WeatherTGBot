package com.example.weather_telegram_bot_0904.model.apidata;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

//Класс для получения информации о погоде
@Component
public class URLInformation {
    @Value("${weather.apikey}")
    private String API_KEY;

//------------------------------Метод получения информации о погоде-----------------------------------------------
    public ArrayList<String> getWeatherInformation(String[] requiredLines, double latitude, double longitude){
        ArrayList<String> values = new ArrayList<>();
        try {
            URL url = new URL("https://api.pirateweather.net/forecast/" + API_KEY + "/" + latitude + "," + longitude + "?units=si&exclude=minutely,hourly,daily,alerts,flags");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() != 200){
                throw new RuntimeException("Failed : HTTP error code" + conn.getResponseCode());
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String output;
            StringBuilder stringBuilder = new StringBuilder();

            while ((output = bufferedReader.readLine()) != null){
                stringBuilder.append(output);
            }
            conn.disconnect();

            JSONObject rootObject = new JSONObject(stringBuilder.toString());

            JSONObject currently = rootObject.getJSONObject("currently");

            //Обработка того, что надо вернуть
            for(int i = 0; i < requiredLines.length; i++){
                switch (requiredLines[i]){
                    case "temperature", "humidity", "apparentTemperature", "windSpeed"-> {
                        values.add(String.valueOf(currently.getDouble(requiredLines[i])));
                    }
                    case "precipType" -> {
                        values.add(currently.getString(requiredLines[i]));
                    }
                    default -> {
                        System.out.println("Нет такого значения");
                    }
                }

            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return values;
    }
}
