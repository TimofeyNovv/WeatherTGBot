package com.example.weather_telegram_bot_0904.controller.processCallBackHandlers;

import com.example.weather_telegram_bot_0904.model.apidata.URLInformation;
import com.example.weather_telegram_bot_0904.model.database.service.CoordinatesService;
import com.example.weather_telegram_bot_0904.view.BotMessages;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;

@Component
public class CallbackProcessor {

    private final List<CallbackHandlerInterface> handlers;

    public CallbackProcessor(List<CallbackHandlerInterface> handlers) {
        this.handlers = handlers;
    }

    public SendMessage process(Update update, BotMessages botMessages, URLInformation urlInformation, ArrayList<String> valuesWeather, CoordinatesService coordinatesService){
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        String call_data = update.getCallbackQuery().getData();
        return handlers.stream()
                .filter(handler -> handler.canHandle(call_data))
                .findFirst()
                .map(handler -> handler.handle(update, botMessages, urlInformation, valuesWeather, coordinatesService))
                .orElseGet(() -> new SendMessage(String.valueOf(chatId), "Неизвестная команда callback processor"));
    }
}
