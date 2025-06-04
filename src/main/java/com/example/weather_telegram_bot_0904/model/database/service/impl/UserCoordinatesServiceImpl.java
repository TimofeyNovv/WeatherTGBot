package com.example.weather_telegram_bot_0904.model.database.service.impl;

import com.example.weather_telegram_bot_0904.model.database.entity.UserCoordinatesEntity;
import com.example.weather_telegram_bot_0904.model.database.repository.UserCoordinatesRepository;
import com.example.weather_telegram_bot_0904.model.database.service.UserCoordinatesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserCoordinatesServiceImpl implements UserCoordinatesService {

    private final UserCoordinatesRepository repository;

    @Override
    @Transactional
    public void saveLatitude(Long userId, Double latitude, Long chatId) {
        UserCoordinatesEntity coordinates = repository.findByUserId(userId)
                .orElse(new UserCoordinatesEntity());
        coordinates.setUserId(userId);
        coordinates.setLatitude(latitude);
        coordinates.setChatId(chatId);
        repository.save(coordinates);
    }

    @Override
    @Transactional
    public void saveLongitude(Long userId, Double longitude, Long chatId) {
        UserCoordinatesEntity coordinates = repository.findByUserId(userId)
                .orElse(new UserCoordinatesEntity());
        coordinates.setUserId(userId);
        coordinates.setLongitude(longitude);
        coordinates.setChatId(chatId);
        repository.save(coordinates);
    }

    @Override
    @Transactional(readOnly = true)
    public Double getLongitude(Long userId){
        UserCoordinatesEntity coordinates = repository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        return coordinates.getLongitude();
    }

    @Override
    @Transactional(readOnly = true)
    public Double getLatitude(Long userId){
        UserCoordinatesEntity coordinates = repository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        return coordinates.getLatitude();
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        UserCoordinatesEntity coordinates =  repository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        repository.delete(coordinates);
    }

    @Override
    @Transactional(readOnly = true)
    public Long getChatId(Long userId){
        UserCoordinatesEntity coordinates = repository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        return coordinates.getUserId();
    }


}
