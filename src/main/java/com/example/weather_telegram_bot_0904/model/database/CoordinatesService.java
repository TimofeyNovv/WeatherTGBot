package com.example.weather_telegram_bot_0904.model.database;

import com.example.weather_telegram_bot_0904.model.database.entity.UserCoordinatesEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CoordinatesService {
    private final UserCoordinatesRepository repository;

    public void saveLatitude(Long userId, Double latitude) {
        UserCoordinatesEntity coordinates = repository.findByUserId(userId)
                .orElse(new UserCoordinatesEntity());
        coordinates.setUserId(userId);
        coordinates.setLatitude(latitude);
        repository.save(coordinates);
    }

    public void saveLongitude(Long userId, Double longitude) {
        UserCoordinatesEntity coordinates = repository.findByUserId(userId)
                .orElse(new UserCoordinatesEntity());
        coordinates.setUserId(userId);
        coordinates.setLongitude(longitude);
        repository.save(coordinates);
    }

    public Double getLongitude(Long userId){
        UserCoordinatesEntity coordinates = repository.findByUserId(userId)
                .orElse(new UserCoordinatesEntity());
        return coordinates.getLongitude();
    }

    public Double getLatitude(Long userId){
        UserCoordinatesEntity coordinates = repository.findByUserId(userId)
                .orElse(new UserCoordinatesEntity());
        return coordinates.getLatitude();
    }
}
