package com.example.weather_telegram_bot_0904.controller.processCalbackRefresh;


import com.example.weather_telegram_bot_0904.model.state.UserStateService;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface CallbackRefreshInterface {
    boolean canHandle(String call_data);

    EditMessageText handle(Update update, UserStateService userStateService);
}
