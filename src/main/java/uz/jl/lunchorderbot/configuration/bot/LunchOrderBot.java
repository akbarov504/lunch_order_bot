package uz.jl.lunchorderbot.configuration.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.jl.lunchorderbot.handlers.main.MainHandler;

public class LunchOrderBot extends TelegramLongPollingBot {
    private final MainHandler handler;

    public LunchOrderBot(MainHandler handler) {
        this.handler = handler;
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            handler.handler(update, this);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "pdp_lunch_order_bot";
    }

    @Override
    public String getBotToken() {
        return "5156191550:AAF7WdEBpHH2jeVuVnlIRaa2upgAdyhGa-w";
    }

    public void executeMessage(BotApiMethod<?> message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void executePhone(SendPhoto message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
