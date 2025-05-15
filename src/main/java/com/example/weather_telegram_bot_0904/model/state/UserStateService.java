package com.example.weather_telegram_bot_0904.model.state;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserStateService {

    private final Map<Long, UserState> userStates = new ConcurrentHashMap<>();

    public void setUserState(Long userId, UserState state) {
        userStates.put(userId, state);
    }

    public UserState getUserState(Long userId) {
        return userStates.getOrDefault(userId, UserState.DEFAULT);
    }
}
