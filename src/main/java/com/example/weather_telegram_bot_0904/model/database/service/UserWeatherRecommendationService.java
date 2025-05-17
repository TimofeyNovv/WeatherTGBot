package com.example.weather_telegram_bot_0904.model.database.service;


import com.example.weather_telegram_bot_0904.model.database.entity.UserWeatherRecommendationEntity;
import com.example.weather_telegram_bot_0904.model.database.repository.UserWeatherRecommendationRepository;
import com.example.weather_telegram_bot_0904.view.BotMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserWeatherRecommendationService {
    private final UserWeatherRecommendationRepository repository;

    public void saveRecommendation(Long userId, Integer minValue, Integer maxValue, String recommendation) {
        UserWeatherRecommendationEntity weatherRecommendation = repository.findByMinValue(minValue)
                .orElse(new UserWeatherRecommendationEntity());
        weatherRecommendation.setUserId(userId);
        weatherRecommendation.setMaxValue(maxValue);
        weatherRecommendation.setMinValue(minValue);
        weatherRecommendation.setRecommendation(recommendation);
        repository.save(weatherRecommendation);
    }

    public List<Integer> getMinValue(Long userId) {
        List<Integer> minValList = new ArrayList<>();
        List<UserWeatherRecommendationEntity> entityList = repository.findByUserId(userId);
        for (int i = 0; i <= entityList.size() - 1; i++) {
            minValList.add(entityList.get(i).getMinValue());
        }
        return minValList;
    }

    public List<Integer> getMaxValue(Long userId) {
        List<Integer> maxValList = new ArrayList<>();
        List<UserWeatherRecommendationEntity> entityList = repository.findByUserId(userId);
        for (int i = 0; i <= entityList.size() - 1; i++) {
            maxValList.add(entityList.get(i).getMaxValue());
        }
        return maxValList;
    }

    public String getRecommendation(Integer minValue) {
        UserWeatherRecommendationEntity userEntity = repository.findByMinValue(minValue)
                .orElse(new UserWeatherRecommendationEntity());
        return userEntity.getRecommendation();
    }

    public boolean setValues(Long userId, Integer minValue, Integer maxValue) {
        List<UserWeatherRecommendationEntity> userEntity = repository.findByUserId(userId);
        boolean isPresent = false;
        for (int i = 0; i < userEntity.size(); i++) {
            if (userEntity.get(i).getMinValue() <= minValue && minValue <= userEntity.get(i).getMaxValue() || userEntity.get(i).getMinValue() <= minValue && maxValue <= userEntity.get(i).getMaxValue()) {
                System.out.println("Значение пересекается с уже введёнными");
                isPresent = true;
                break;
            }
        }
        if (!isPresent) {
            System.out.println("сохранение в бд");
            UserWeatherRecommendationEntity weatherRecommendation = new UserWeatherRecommendationEntity();
            weatherRecommendation.setMinValue(minValue);
            weatherRecommendation.setMaxValue(maxValue);
            weatherRecommendation.setUserId(userId);
            repository.save(weatherRecommendation);
        }
        return isPresent;
    }
}
