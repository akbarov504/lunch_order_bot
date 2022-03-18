package uz.jl.lunchorderbot.handlers.main;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.jl.lunchorderbot.configuration.bot.LunchOrderBot;
import uz.jl.lunchorderbot.configuration.state.SessionState;
import uz.jl.lunchorderbot.enums.state.StateEnum;
import uz.jl.lunchorderbot.handlers.BaseHandler;
import uz.jl.lunchorderbot.handlers.auth.AuthHandler;
import uz.jl.lunchorderbot.handlers.home.HomeHandler;
import uz.jl.lunchorderbot.repository.auth.AuthRepository;
import uz.jl.lunchorderbot.service.auth.AuthService;

import java.util.Objects;

public class MainHandler implements BaseHandler {
    private final AuthHandler authHandler;
    private final HomeHandler homeHandler;

    public MainHandler() {
        this.authHandler = new AuthHandler(new AuthService(new AuthRepository()));
        this.homeHandler = new HomeHandler();
    }

    @Override
    public void handler(Update update, LunchOrderBot bot) throws TelegramApiException {
        if (update.hasMessage() || update.hasCallbackQuery()) {
            String chatId;
            if (update.hasMessage()) {
                chatId = update.getMessage().getChatId().toString();
            } else {
                chatId = update.getCallbackQuery().getMessage().getChatId().toString();
            }

            if (Objects.isNull(SessionState.STATE_MAP.get(chatId))) {
                SessionState.STATE_MAP.put(chatId, StateEnum.ANONYMOUS);
            }

            switch (SessionState.STATE_MAP.get(chatId)) {
                case ANONYMOUS, CHOOSE_MAIN_MENU, LOGIN, LOGIN_USERNAME, LOGIN_PASSWORD, REGISTER, REGISTER_USERNAME, REGISTER_PASSWORD, REGISTER_FULL_NAME, REGISTER_PHONE, REGISTER_LANGUAGE, REGISTER_POSITION, REGISTER_DEPARTMENT -> authHandler.handler(update, bot);
                case AUTH_HOME, ORDERING, COMPLAINT_LIST, COMPLAINT_WRITE -> homeHandler.handler(update, bot);
            }
        }
    }
}
