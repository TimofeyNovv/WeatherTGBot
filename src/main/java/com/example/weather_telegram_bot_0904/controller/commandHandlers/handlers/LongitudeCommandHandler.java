package com.example.weather_telegram_bot_0904.controller.commandHandlers.handlers;

import com.example.weather_telegram_bot_0904.controller.commandHandlers.CommandHandlerInterface;
import com.example.weather_telegram_bot_0904.model.state.UserState;
import com.example.weather_telegram_bot_0904.model.state.UserStateService;
import com.example.weather_telegram_bot_0904.view.BotMessages;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class LongitudeCommandHandler implements CommandHandlerInterface {

    @Override
    public boolean canHandle(String command, UserState userState) {
        return "/longitude".equals(command) && userState == UserState.DEFAULT;
    }

    @Override
    public SendMessage handle(Update update, UserStateService userStateService, BotMessages botMessages) {
        Long chatId = update.getMessage().getChatId();
        userStateService.setUserState(chatId, UserState.AWAITING_INPUT_LONGITUDE);
        return botMessages.sendMessage(chatId, "Введите долготу нужной точки");
    }
}
