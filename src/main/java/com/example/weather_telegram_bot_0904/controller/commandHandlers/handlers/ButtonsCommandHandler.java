package com.example.weather_telegram_bot_0904.controller.commandHandlers.handlers;

import com.example.weather_telegram_bot_0904.controller.commandHandlers.CommandHandlerInterface;
import com.example.weather_telegram_bot_0904.model.state.UserState;
import com.example.weather_telegram_bot_0904.model.state.UserStateService;
import com.example.weather_telegram_bot_0904.view.BotMessages;
import com.example.weather_telegram_bot_0904.view.inlineKeyboards.InlineKeyboardWeatherButtons;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@Getter
public class ButtonsCommandHandler implements CommandHandlerInterface {

    private final InlineKeyboardWeatherButtons inlineKeyboard = new InlineKeyboardWeatherButtons();

    @Override
    public boolean canHandle(String command, UserState userState) {
        return "/buttons".equals(command) && userState == UserState.DEFAULT;
    }

    @Override
    public SendMessage handle(Update update, UserStateService userStateService, BotMessages botMessages) {
        return inlineKeyboard.hermitageInlineKeyboardAb(update.getMessage().getChatId(),
                "Выберите кнопку");
    }
}
