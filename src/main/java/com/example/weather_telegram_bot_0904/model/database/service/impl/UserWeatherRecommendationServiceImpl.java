package com.example.weather_telegram_bot_0904.model.database.service.impl;


import com.example.weather_telegram_bot_0904.model.database.entity.UserCoordinatesEntity;
import com.example.weather_telegram_bot_0904.model.database.entity.UserWeatherRecommendationEntity;
import com.example.weather_telegram_bot_0904.model.database.repository.UserCoordinatesRepository;
import com.example.weather_telegram_bot_0904.model.database.repository.UserWeatherRecommendationRepository;
import com.example.weather_telegram_bot_0904.model.database.service.UserWeatherRecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserWeatherRecommendationServiceImpl implements UserWeatherRecommendationService {

    private final UserWeatherRecommendationRepository recommendationRepository;

    private final UserCoordinatesRepository coordinatesRepository;

    @Override
    @Transactional(readOnly = true)
    public String getRecommendation(Long id) {
        UserWeatherRecommendationEntity recommendationEntity = recommendationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Рекомендация с id " + id + " не найдена"));
        return recommendationEntity.getRecommendation();
    }

    @Override
    @Transactional(readOnly = true)
    public Long ifRecommendation(Long userId, Double value) {
        Long ifhave = 0L;
        UserCoordinatesEntity user = coordinatesRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        for (UserWeatherRecommendationEntity recommendationEntity : user.getRecommendations()) {
            if (value >= recommendationEntity.getMinValue() && value <= recommendationEntity.getMaxValue()) {
                return recommendationEntity.getId();
            }
        }
        return ifhave;
    }

    @Override
    @Transactional
    public boolean setValues(Long userId, Integer minValue, Integer maxValue) {
        UserCoordinatesEntity user = coordinatesRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        boolean isOverlapping = user.getRecommendations().stream()
                .anyMatch(recommendation ->
                        (minValue <= recommendation.getMaxValue() &&
                                maxValue >= recommendation.getMinValue())
                );

        if (isOverlapping) {
            return true;
        }

        UserWeatherRecommendationEntity newRecommendation = new UserWeatherRecommendationEntity();
        newRecommendation.setMinValue(minValue);
        newRecommendation.setMaxValue(maxValue);
        newRecommendation.setUser(user);  // Устанавливаю связь

        user.getRecommendations().add(newRecommendation);

        recommendationRepository.save(newRecommendation);

        return false;
    }

    @Override
    @Transactional
    public boolean setRecommendation(Long userId, int inputValue, String recommendation) {
        boolean exists = false;
        UserCoordinatesEntity user = coordinatesRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        for (UserWeatherRecommendationEntity recommendationEntity : user.getRecommendations()) {
            if (inputValue >= recommendationEntity.getMinValue() && inputValue <= recommendationEntity.getMaxValue()) {
                System.out.println("не подходит");
                recommendationEntity.setRecommendation(recommendation);
                recommendationRepository.save(recommendationEntity);
                return true;
            }
        }
        return exists;
    }
}
