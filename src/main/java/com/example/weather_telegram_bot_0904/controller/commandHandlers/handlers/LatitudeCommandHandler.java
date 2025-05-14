package com.example.weather_telegram_bot_0904.controller.commandHandlers.handlers;

import com.example.weather_telegram_bot_0904.model.state.UserState;
import com.example.weather_telegram_bot_0904.controller.commandHandlers.CommandHandlerInterface;
import com.example.weather_telegram_bot_0904.model.state.UserStateService;
import com.example.weather_telegram_bot_0904.view.BotMessages;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.Map;

@Component
public class LatitudeCommandHandler implements CommandHandlerInterface {

    private Map<Long, UserState> userState = new HashMap<>();
    @Override
    public boolean canHandle(String command, UserState userState) {
        return "/latitude".equals(command) && userState == UserState.DEFAULT;
    }

    @Override
    public SendMessage handle(Update update, UserStateService userStateService, BotMessages botMessages) {
        Long chatId = update.getMessage().getChatId();
        userStateService.setUserState(chatId, UserState.AWAITING_INPUT_LATITUDE);
        return botMessages.sendMessage(chatId, "Введите широту нужной точки");
    }
}
