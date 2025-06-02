package com.example.weather_telegram_bot_0904.controller.commandHandlers.handlers;

import com.example.weather_telegram_bot_0904.controller.commandHandlers.CommandHandlerInterface;
import com.example.weather_telegram_bot_0904.model.state.UserState;
import com.example.weather_telegram_bot_0904.model.state.UserStateService;
import com.example.weather_telegram_bot_0904.view.BotMessages;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class ButtonsTestCommandHandler implements CommandHandlerInterface {
    @Override
    public boolean canHandle(String command, UserState userState) {
        return command.equals("/buttonstest");
    }

    @Override
    public SendMessage handle(Update update, UserStateService userStateService, BotMessages botMessages) {
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();

        // Создаем кнопки
        List<InlineKeyboardButton> row = new ArrayList<>();
        row.add(InlineKeyboardButton.builder()
                .text("Кнопка 1")
                .callbackData("button1")
                .build());
        keyboard.setKeyboard(List.of(row));

        return SendMessage.builder()
                    .chatId(update.getMessage().getChatId().toString())
                    .text("Выберите действие:")
                    .replyMarkup(keyboard)
                    .build();

    }
}
