package com.example.weather_telegram_bot_0904.controller.commandHandlers.handlers;

import com.example.weather_telegram_bot_0904.controller.commandHandlers.CommandHandlerInterface;
import com.example.weather_telegram_bot_0904.model.state.UserState;
import com.example.weather_telegram_bot_0904.model.state.UserStateService;
import com.example.weather_telegram_bot_0904.view.BotMessages;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@Getter
public class StartCommandHandler implements CommandHandlerInterface {

    private Long chatId;

    @Override
    public boolean canHandle(String command, UserState userState) {
        return command.equals("/start");
    }

    @Override
    public SendMessage handle(Update update, UserStateService userStateService, BotMessages botMessages) {
        chatId = update.getMessage().getChatId();

        return botMessages.sendMessage(chatId, "Здравствуйте, это бот предназначен для отправления текущей погоды, и рекомендаций по одежде, которые можно написать под себя.\n" +
                "погода определяется по координатам широты и долготы. Их вы должны ввести сами при помощи команд /longitude и /latitude. \n" +
                "Например координаты Москвы это 55,45 широты и 37,37 долготы\n" +
                "Санкт-Петербург это 59,57 широты и 30,19 долготы");
    }
}
