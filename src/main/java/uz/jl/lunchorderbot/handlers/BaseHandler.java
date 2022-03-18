package uz.jl.lunchorderbot.handlers;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.jl.lunchorderbot.configuration.bot.LunchOrderBot;

public interface BaseHandler {
    void handler(Update update, LunchOrderBot bot) throws TelegramApiException;
}
