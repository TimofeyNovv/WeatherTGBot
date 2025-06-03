package com.example.weather_telegram_bot_0904.model.database.service.impl;

import com.example.weather_telegram_bot_0904.model.database.entity.UserCoordinatesEntity;
import com.example.weather_telegram_bot_0904.model.database.repository.UserCoordinatesRepository;
import com.example.weather_telegram_bot_0904.model.database.service.UserCoordinatesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCoordinatesServiceImpl implements UserCoordinatesService {

    private final UserCoordinatesRepository repository;

    @Override
    public void saveLatitude(Long userId, Double latitude, Long chatId) {
        UserCoordinatesEntity coordinates = repository.findByUserId(userId)
                .orElse(new UserCoordinatesEntity());
        coordinates.setUserId(userId);
        coordinates.setLatitude(latitude);
        coordinates.setChatId(chatId);
        repository.save(coordinates);
    }

    @Override
    public void saveLongitude(Long userId, Double longitude, Long chatId) {
        UserCoordinatesEntity coordinates = repository.findByUserId(userId)
                .orElse(new UserCoordinatesEntity());
        coordinates.setUserId(userId);
        coordinates.setLongitude(longitude);
        coordinates.setChatId(chatId);
        repository.save(coordinates);
    }

    @Override
    public Double getLongitude(Long userId){
        UserCoordinatesEntity coordinates = repository.findByUserId(userId)
                .orElse(new UserCoordinatesEntity());
        return coordinates.getLongitude();
    }

    @Override
    public Double getLatitude(Long userId){
        UserCoordinatesEntity coordinates = repository.findByUserId(userId)
                .orElse(new UserCoordinatesEntity());
        return coordinates.getLatitude();
    }

    @Override
    public Long getUserId(Long chatId){
        UserCoordinatesEntity coordinates = repository.findByChatId(chatId)
                .orElse(new UserCoordinatesEntity());
        return coordinates.getUserId();
    }

    @Override
    public Long getChatId(Long userId){
        UserCoordinatesEntity coordinates = repository.findByChatId(userId)
                .orElse(new UserCoordinatesEntity());
        return coordinates.getUserId();
    }
}
