package com.example.weather_telegram_bot_0904.view;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;

import java.util.List;

@Component
public class CommandMenuCr {

    //----------------------------Метод для создания кнопки меню у поля ввода-----------------------------------------
    public SetMyCommands setupCommandMenu() {
        List<BotCommand> commands = List.of(
                new BotCommand("/start", "Запустить бота и получить панель управления"),
                new BotCommand("/latitude", "Ввести нужную широту"),
                new BotCommand("/longitude", "Ввести нужную долготу"),
                new BotCommand("/settings", "Узнать текущую долготу и ширину")
        );

        return new SetMyCommands(commands, new BotCommandScopeDefault(), null);
    }
}
