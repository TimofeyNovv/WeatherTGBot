package com.example.weather_telegram_bot_0904.model.database.service;

public interface UserCoordinatesService {

    void saveLatitude(Long userId, Double latitude, Long chatId);

    public void saveLongitude(Long userId, Double longitude, Long chatId);

    public Double getLongitude(Long userId);

    public Double getLatitude(Long userId);

    public Long getChatId(Long userId);

    public Long getUserId(Long chatId);
    }
