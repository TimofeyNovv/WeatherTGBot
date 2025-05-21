package com.example.weather_telegram_bot_0904.model.apidata;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class URLDataFetcher {
    @Value("${weather.apikey}")
    private String API_KEY;

    public JSONObject getInfJson(double latitude, double longitude) {
        JSONObject currently = new JSONObject();
        try {
            URL url = new URL("https://api.pirateweather.net/forecast/" + API_KEY + "/" + latitude + "," + longitude + "?units=si&exclude=minutely,hourly,daily,alerts,flags");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code" + conn.getResponseCode());
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String output;
            StringBuilder stringBuilder = new StringBuilder();

            while ((output = bufferedReader.readLine()) != null) {
                stringBuilder.append(output);
            }
            conn.disconnect();

            JSONObject rootObject = new JSONObject(stringBuilder.toString());

            currently = rootObject.getJSONObject("currently");

        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return currently;
    }
}
