package com.example.weather_telegram_bot_0904.model.database;


import com.example.weather_telegram_bot_0904.model.database.entity.UserCoordinatesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCoordinatesRepository extends JpaRepository<UserCoordinatesEntity, Long> {
    Optional<UserCoordinatesEntity> findByUserId(Long userId);
}