package com.example.weather_telegram_bot_0904.controller.commandHandlers.handlers;

import com.example.weather_telegram_bot_0904.controller.commandHandlers.CommandHandlerInterface;
import com.example.weather_telegram_bot_0904.model.state.UserState;
import com.example.weather_telegram_bot_0904.model.state.UserStateService;
import com.example.weather_telegram_bot_0904.view.BotMessages;
import com.example.weather_telegram_bot_0904.view.inlineKeyboards.startIK.StartInlineKeyboard;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@Getter
public class StartCommandHandler implements CommandHandlerInterface {

    private Long chatId;

    private final StartInlineKeyboard startInlineKeyboard = new StartInlineKeyboard();
    @Override
    public boolean canHandle(String command, UserState userState) {
        return command.equals("/start");
    }

    @Override
    public SendMessage handle(Update update, UserStateService userStateService, BotMessages botMessages) {
        chatId = update.getMessage().getChatId();

        return SendMessage.builder()
                .chatId(update.getMessage().getChatId().toString())
                .text("Здравствуйте, это бот предназначен для отправления текущей погоды, и рекомендаций по одежде, которые можно написать под себя." +
                        "погода определяется по координатам широты и долготы. Их вы можете ввести сами при помощи команд.")
                .replyMarkup(startInlineKeyboard.createKeyBoard())
                .build();
        }
}
