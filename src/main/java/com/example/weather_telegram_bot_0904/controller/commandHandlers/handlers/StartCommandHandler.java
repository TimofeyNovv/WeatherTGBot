package com.example.weather_telegram_bot_0904.controller.commandHandlers.handlers;

import com.example.weather_telegram_bot_0904.controller.commandHandlers.CommandHandlerInterface;
import com.example.weather_telegram_bot_0904.model.state.UserState;
import com.example.weather_telegram_bot_0904.model.state.UserStateService;
import com.example.weather_telegram_bot_0904.view.BotMessages;
import com.example.weather_telegram_bot_0904.view.InlineKeyboard;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@Getter
public class StartCommandHandler implements CommandHandlerInterface {

    private final InlineKeyboard inlineKeyboard = new InlineKeyboard();

    private Long chatId;

    @Override
    public boolean canHandle(String command, UserState userState) {
        return "/start".equals(command) && userState == UserState.DEFAULT;
    }

    @Override
    public SendMessage handle(Update update, UserStateService userStateService, BotMessages botMessages) {
        chatId = update.getMessage().getChatId();
        return inlineKeyboard.hermitageInlineKeyboardAb(chatId,
                "Выберите кнопку");
    }
}
