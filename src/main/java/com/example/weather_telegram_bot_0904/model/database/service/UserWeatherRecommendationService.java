package com.example.weather_telegram_bot_0904.model.database.service;

import java.util.List;

public interface UserWeatherRecommendationService {

    public List<Integer> getMinValue(Long userId);

    public List<Integer> getMaxValue(Long userId);

    public String getRecommendation(Long id);

    public Long ifRecommendation(Long userId, Double value);

    public boolean setValues(Long userId, Integer minValue, Integer maxValue);

    public boolean setRecommendation(Long userId, int inputValue, String recommendation);
    }
