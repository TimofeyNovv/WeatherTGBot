package com.example.weather_telegram_bot_0904.view;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;

import java.util.List;

@Component
public class CommandMenuCr {

    public SetMyCommands setupCommandMenu() {
        List<BotCommand> commands = List.of(
                new BotCommand("/buttons", "Получить панель управления"),
                new BotCommand("/latitude", "Ввести нужную широту"),
                new BotCommand("/longitude", "Ввести нужную долготу"),
                new BotCommand("/settings", "Узнать текущую долготу и ширину"),
                new BotCommand("/range", "Ввести диапазон значений"),
                new BotCommand("/recomm", "Ввести рекомендацию"),
                new BotCommand("/exit", "Выйти из состояния ввода")
        );

        return new SetMyCommands(commands, new BotCommandScopeDefault(), null);
    }
}
