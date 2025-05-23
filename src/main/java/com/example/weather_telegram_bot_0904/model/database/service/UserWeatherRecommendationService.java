package com.example.weather_telegram_bot_0904.model.database.service;


import com.example.weather_telegram_bot_0904.model.database.entity.UserWeatherRecommendationEntity;
import com.example.weather_telegram_bot_0904.model.database.repository.UserWeatherRecommendationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserWeatherRecommendationService {
    private final UserWeatherRecommendationRepository repository;

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

    public String getRecommendation(Long id) {
        UserWeatherRecommendationEntity userEntity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Пользователь с id " + id + " не найден"));
        return userEntity.getRecommendation();
    }

    public Long ifRecommendation(Long userId, Double value) {
        Long ifhave = 0L;
        List<UserWeatherRecommendationEntity> userEntity = repository.findByUserId(userId);
        for (int i = 0; i < userEntity.size(); i++) {
            if (value >= userEntity.get(i).getMinValue() && value <= userEntity.get(i).getMaxValue()) {
                ifhave = userEntity.get(i).getId();
                break;
            }
        }
        return ifhave;
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
            UserWeatherRecommendationEntity weatherRecommendation = new UserWeatherRecommendationEntity();
            weatherRecommendation.setMinValue(minValue);
            weatherRecommendation.setMaxValue(maxValue);
            weatherRecommendation.setUserId(userId);
            repository.save(weatherRecommendation);
        }
        return isPresent;
    }

    public boolean setRecommendation(Long userId, int inputValue, String recommendation) {
        boolean exists = false;
        List<UserWeatherRecommendationEntity> userEntity = repository.findByUserId(userId);
        for (int i = 0; i < userEntity.size(); i++) {
            for (int value = userEntity.get(i).getMinValue(); value <= userEntity.get(i).getMaxValue(); value++) {
                if (inputValue == value) {
                    userEntity.get(i).setRecommendation(recommendation);
                    repository.save(userEntity.get(i));
                    exists = true;
                    break;
                }
            }
            if (exists) {
                break;
            }
        }
        return exists;
    }
}
