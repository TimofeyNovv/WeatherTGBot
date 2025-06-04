package com.example.weather_telegram_bot_0904.controller.commandHandlers.handlers;

import com.example.weather_telegram_bot_0904.controller.commandHandlers.CommandHandlerInterface;
import com.example.weather_telegram_bot_0904.model.database.service.impl.UserCoordinatesServiceImpl;
import com.example.weather_telegram_bot_0904.model.state.UserState;
import com.example.weather_telegram_bot_0904.model.state.UserStateService;
import com.example.weather_telegram_bot_0904.view.BotMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class SettingsHandler implements CommandHandlerInterface {

    private final UserCoordinatesServiceImpl coordinatesService;

    @Override
    public boolean canHandle(String command, UserState userState) {
        return "/settings".equals(command) && userState == UserState.DEFAULT;
    }

    @Override
    public SendMessage handle(Update update, UserStateService userStateService, BotMessages botMessages) {
        Long userId = update.getMessage().getFrom().getId();
        return botMessages.sendMessage(update.getMessage().getChatId(), "Текущая высота = " +
                coordinatesService.getLatitude(userId) +
                "\n Текущая широта = " + coordinatesService.getLongitude(userId));
         }
}
