package com.example.weather_telegram_bot_0904.model.database.service;


import com.example.weather_telegram_bot_0904.model.database.entity.UserWeatherRecommendationEntity;
import com.example.weather_telegram_bot_0904.model.database.repository.UserWeatherRecommendationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserWeatherRecommendationService {
    private final UserWeatherRecommendationRepository repository;

    public void saveRecommendation(Long userId, Integer minValue, Integer maxValue, String recommendation){
        UserWeatherRecommendationEntity weatherRecommendation = repository.findByMinValue(minValue)
                .orElse(new UserWeatherRecommendationEntity());
        weatherRecommendation.setUserId(userId);
        weatherRecommendation.setMaxValue(maxValue);
        weatherRecommendation.setMinValue(minValue);
        weatherRecommendation.setRecommendation(recommendation);
        repository.save(weatherRecommendation);
    }

    public void getRecommendation(Long userId){
        List<UserWeatherRecommendationEntity> weatherRecommendation = repository.findByUserId(userId);
        for (int i = 0; i <= weatherRecommendation.size() - 1; i++){
            System.out.println(weatherRecommendation.get(i).getMaxValue());
        }
    }
}
