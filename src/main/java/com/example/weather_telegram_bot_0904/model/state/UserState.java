package com.example.weather_telegram_bot_0904.model.state;


//Состояния пользователя при вводе координат

public enum UserState {
    DEFAULT,
    AWAITING_INPUT_LATITUDE,
    AWAITING_INPUT_LONGITUDE
}