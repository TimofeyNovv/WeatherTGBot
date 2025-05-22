package com.example.weather_telegram_bot_0904.model.database.repository;

import com.example.weather_telegram_bot_0904.model.database.entity.UserWeatherRecommendationEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserWeatherRecommendationRepository extends JpaRepository<UserWeatherRecommendationEntity, Long> {
    Optional<UserWeatherRecommendationEntity> findByMinValue(Integer minValue);

    @NonNull
    Optional<UserWeatherRecommendationEntity> findById(@NonNull Long id);

    List<UserWeatherRecommendationEntity> findByUserId(Long userId);
}
