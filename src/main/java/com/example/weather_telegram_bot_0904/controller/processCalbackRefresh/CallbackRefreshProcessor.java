package com.example.weather_telegram_bot_0904.controller.processCalbackRefresh;

import com.example.weather_telegram_bot_0904.controller.commandHandlers.CommandHandlerInterface;
import com.example.weather_telegram_bot_0904.model.state.UserStateService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Component
public class CallbackRefreshProcessor {
    private final List<CallbackRefreshInterface> handlers;

    public CallbackRefreshProcessor(List<CallbackRefreshInterface> handlers) {
        this.handlers = handlers;
    }

    public EditMessageText process(Update update, UserStateService userStateService) {
        String call_data = update.getCallbackQuery().getData();

        return handlers.stream()
                .filter(handler -> handler.canHandle(call_data))
                .findFirst()
                .map(handler -> handler.handle(update, userStateService))
                .orElseGet(() -> null);
    }
}
