package com.example.weather_telegram_bot_0904.controller.commandHandlers.handlers;

import com.example.weather_telegram_bot_0904.controller.commandHandlers.CommandHandlerInterface;
import com.example.weather_telegram_bot_0904.model.state.UserState;
import com.example.weather_telegram_bot_0904.model.state.UserStateService;
import com.example.weather_telegram_bot_0904.view.BotMessages;
import com.example.weather_telegram_bot_0904.view.inlineKeyboards.InlineKeyboardButtonTest;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;


@Component
public class ButtonsTestCommandHandler implements CommandHandlerInterface {

    private final InlineKeyboardButtonTest inlineKeyboardButtonTest = new InlineKeyboardButtonTest();

    @Override
    public boolean canHandle(String command, UserState userState) {
        return command.equals("/buttonstest");
    }

    @Override
    public SendMessage handle(Update update, UserStateService userStateService, BotMessages botMessages) {


        return SendMessage.builder()
                    .chatId(update.getMessage().getChatId().toString())
                    .text("Выберите действие:")
                    .replyMarkup(inlineKeyboardButtonTest.createKeyBoard())
                    .build();

    }
}
