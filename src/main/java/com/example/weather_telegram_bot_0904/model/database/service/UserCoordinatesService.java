package com.example.weather_telegram_bot_0904.model.database.service;

import com.example.weather_telegram_bot_0904.model.database.entity.UserCoordinatesEntity;

public interface UserCoordinatesService {

    void saveLatitude(Long userId, Double latitude, Long chatId);

     void saveLongitude(Long userId, Double longitude, Long chatId);

     Double getLongitude(Long userId);

     Double getLatitude(Long userId);

     Long getChatId(Long userId);

    void deleteUser(Long userId);
    }
