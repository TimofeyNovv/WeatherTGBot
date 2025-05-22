package com.example.weather_telegram_bot_0904.model.apidata;

import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DataURLService {
    private final URLDataFetcher urlDataFetcher;

    public Double getTemperature(double latitude, double longitude) {
        return urlDataFetcher.getInfJson(latitude, longitude).getDouble("temperature");
    }

    public Double getHumidity(double latitude, double longitude) {
        return urlDataFetcher.getInfJson(latitude, longitude).getDouble("humidity");
    }

    public Double getApparentTemperature(double latitude, double longitude) {
        return urlDataFetcher.getInfJson(latitude, longitude).getDouble("apparentTemperature");
    }

    public Double getWindSpeed(double latitude, double longitude) {
        return urlDataFetcher.getInfJson(latitude, longitude).getDouble("windSpeed");
    }

    public String getPrecipType(double latitude, double longitude) {
        return urlDataFetcher.getInfJson(latitude, longitude).getString("precipType");
    }
}
