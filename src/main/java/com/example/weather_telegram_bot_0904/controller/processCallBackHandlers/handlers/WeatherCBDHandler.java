package com.example.weather_telegram_bot_0904.controller.processCallBackHandlers.handlers;

import com.example.weather_telegram_bot_0904.controller.processCallBackHandlers.CallbackHandlerInterface;
import com.example.weather_telegram_bot_0904.model.apidata.URLInformation;
import com.example.weather_telegram_bot_0904.model.database.service.CoordinatesService;
import com.example.weather_telegram_bot_0904.view.BotMessages;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;

@Component
public class WeatherCBDHandler implements CallbackHandlerInterface {

    @Override
    public boolean canHandle(String call_data) {
        return call_data.equals("WeatherCBD");
    }

    @Override
    public SendMessage handle(Update update, BotMessages botMessages, URLInformation urlInformation, ArrayList<String> valuesWeather,  CoordinatesService coordinatesService) {
        Long userId = update.getCallbackQuery().getFrom().getId();
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        valuesWeather = urlInformation.getWeatherInformation(new String[]{"temperature", "humidity", "apparentTemperature", "windSpeed", "precipType"}, coordinatesService.getLatitude(userId), coordinatesService.getLongitude(userId));
        return botMessages.sendMessage(chatId, "Температура - " + valuesWeather.get(0) + "°C" + "\nВлажность - " + valuesWeather.get(1) + "%" + "\nТемпература ощущается как - " + valuesWeather.get(2) + "°C" + "\nСкорость ветра - " + valuesWeather.get(3) + "м/с" + "\nОсадки -" + valuesWeather.get(4));
    }
}
