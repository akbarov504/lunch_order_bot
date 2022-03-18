package uz.jl.lunchorderbot.configuration.bot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import uz.jl.lunchorderbot.handlers.main.MainHandler;

public class BotInit {
    public static void botInit() {
        try {
            TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
            api.registerBot(new LunchOrderBot(new MainHandler()));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        System.out.println("Bot Successfully started");
    }
}
