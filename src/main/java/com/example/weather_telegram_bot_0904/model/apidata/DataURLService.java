package com.example.weather_telegram_bot_0904.model.apidata;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DataURLService {
    private final URLDataFetcher urlDataFetcher;

    public Double getTemperature(double latitude, double longitude) {
        return urlDataFetcher.getInfJson(latitude, longitude).getDouble("temperature");
    }
}
