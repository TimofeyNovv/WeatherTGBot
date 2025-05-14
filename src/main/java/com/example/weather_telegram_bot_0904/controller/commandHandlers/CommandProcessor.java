package com.example.weather_telegram_bot_0904.controller.commandHandlers;

import com.example.weather_telegram_bot_0904.model.state.UserState;
import com.example.weather_telegram_bot_0904.model.state.UserStateService;
import com.example.weather_telegram_bot_0904.view.BotMessages;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Component
public class CommandProcessor {
    private final List<CommandHandlerInterface> handlers;

    public CommandProcessor(List<CommandHandlerInterface> handlers) {
        this.handlers = handlers;
    }

    public SendMessage process(Update update, UserStateService userStateService, BotMessages botMessages) {
        Long chatId = update.getMessage().getChatId();

        String command = update.getMessage().getText();
        UserState currentState = userStateService.getUserState(chatId);
        // Ищем подходящий обработчик
        return handlers.stream()
                .filter(handler -> handler.canHandle(command, currentState))
                .findFirst()
                .map(handler -> handler.handle(update, userStateService, botMessages))
                .orElseGet(() -> new SendMessage(String.valueOf(chatId), "Неизвестная команда com processor"));
    }


}