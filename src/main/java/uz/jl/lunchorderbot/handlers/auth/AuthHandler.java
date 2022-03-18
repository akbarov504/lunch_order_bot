package uz.jl.lunchorderbot.handlers.auth;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import uz.jl.lunchorderbot.configuration.bot.LunchOrderBot;
import uz.jl.lunchorderbot.configuration.map.DepartmentMap;
import uz.jl.lunchorderbot.configuration.map.LoginDtoMap;
import uz.jl.lunchorderbot.configuration.map.UserCreateDtoMap;
import uz.jl.lunchorderbot.configuration.state.SessionState;
import uz.jl.lunchorderbot.dto.auth.AuthLoginDto;
import uz.jl.lunchorderbot.dto.department.DepartmentGetDto;
import uz.jl.lunchorderbot.dto.user.UserAcceptDepartmentDto;
import uz.jl.lunchorderbot.dto.user.UserCreateDto;
import uz.jl.lunchorderbot.dto.user.UserGetDto;
import uz.jl.lunchorderbot.enums.state.StateEnum;
import uz.jl.lunchorderbot.handlers.AbstractHandler;
import uz.jl.lunchorderbot.keyboard.InlineKeyboard;
import uz.jl.lunchorderbot.keyboard.ReplyKeyboard;
import uz.jl.lunchorderbot.repository.department.DepartmentRepository;
import uz.jl.lunchorderbot.repository.user.UserRepository;
import uz.jl.lunchorderbot.service.auth.AuthService;
import uz.jl.lunchorderbot.service.department.DepartmentService;
import uz.jl.lunchorderbot.service.user.UserService;
import uz.jl.lunchorderbot.util.Emoji;

public class AuthHandler extends AbstractHandler<AuthService> {
    public AuthHandler(AuthService service) {
        super(service);
    }

    @Override
    public void handler(Update update, LunchOrderBot bot) {
        UserService userService = new UserService(new UserRepository());
        if (update.hasMessage()) {
            String chatId = update.getMessage().getChatId().toString();
            if (update.getMessage().hasText()) {
                switch (SessionState.STATE_MAP.get(chatId)) {
                    case ANONYMOUS -> {
                        SendMessage message = new SendMessage(chatId, "Choose the menu" + " " + Emoji.BOTTOM);
                        message.setReplyMarkup(ReplyKeyboard.mainMenu());
                        bot.executeMessage(message);
                        SessionState.setState(chatId, StateEnum.CHOOSE_MAIN_MENU);
                    }
                    case CHOOSE_MAIN_MENU -> {
                        if (update.getMessage().getText().equals("Login" + " " + Emoji.LOGIN)) {
                            ReplyKeyboardRemove remove = new ReplyKeyboardRemove(true);
                            SendMessage message = new SendMessage(chatId, "We started to login" + " " + Emoji.SMILE);
                            message.setReplyMarkup(remove);
                            bot.executeMessage(message);
                            SessionState.setState(chatId, StateEnum.LOGIN);
                        } else if (update.getMessage().getText().equals("Register" + " " + Emoji.REGISTER)) {
                            ReplyKeyboardRemove remove = new ReplyKeyboardRemove(true);
                            SendMessage message = new SendMessage(chatId, "We started to register" + " " + Emoji.SMILE);
                            message.setReplyMarkup(remove);
                            bot.executeMessage(message);
                            SessionState.setState(chatId, StateEnum.REGISTER);
                        } else {
                            DeleteMessage deleteMessage = new DeleteMessage(chatId, update.getMessage().getMessageId());
                            SendMessage message = new SendMessage(chatId, "Wrong choose" + " " + Emoji.UPSET);
                            bot.executeMessage(deleteMessage);
                            bot.executeMessage(message);
                        }
                    }
                    case LOGIN -> {
                        SendMessage message = new SendMessage(chatId, "Enter your username" + " " + Emoji.WRITE);
                        bot.executeMessage(message);
                        SessionState.setState(chatId, StateEnum.LOGIN_USERNAME);
                    }
                    case LOGIN_USERNAME -> {
                        AuthLoginDto dto = new AuthLoginDto();
                        dto.setUsername(update.getMessage().getText());
                        LoginDtoMap.LOGIN_DTO_MAP.put(chatId, dto);
                        SendMessage message = new SendMessage(chatId, "Enter your password" + " " + Emoji.WRITE);
                        bot.executeMessage(message);
                        SessionState.setState(chatId, StateEnum.LOGIN_PASSWORD);
                    }
                    case LOGIN_PASSWORD -> {
                        LoginDtoMap.LOGIN_DTO_MAP.get(chatId).setPassword(update.getMessage().getText());
                        service.login(LoginDtoMap.LOGIN_DTO_MAP.get(chatId), chatId);
                        SendMessage message = new SendMessage(chatId, "Successfully login" + " " + Emoji.ACCEPT);
                        message.setReplyMarkup(ReplyKeyboard.menuMenu());
                        bot.executeMessage(message);
                        SessionState.setState(chatId, StateEnum.AUTH_HOME);
                    }
                    case REGISTER -> {
                        SendMessage message = new SendMessage(chatId, "Enter your new username" + " " + Emoji.WRITE);
                        bot.executeMessage(message);
                        SessionState.setState(chatId, StateEnum.REGISTER_USERNAME);
                    }
                    case REGISTER_USERNAME -> {
                        UserCreateDto dto = new UserCreateDto();
                        dto.setUsername(update.getMessage().getText());
                        dto.setChatId(Integer.parseInt(chatId));
                        UserCreateDtoMap.USER_CREATE_DTO_MAP.put(chatId, dto);
                        SendMessage message = new SendMessage(chatId, "Enter your new password" + " " + Emoji.WRITE);
                        bot.executeMessage(message);
                        SessionState.setState(chatId, StateEnum.REGISTER_PASSWORD);
                    }
                    case REGISTER_PASSWORD -> {
                        UserCreateDtoMap.USER_CREATE_DTO_MAP.get(chatId).setPassword(update.getMessage().getText());
                        SendMessage message = new SendMessage(chatId, "Enter your new full name" + " " + Emoji.WRITE);
                        bot.executeMessage(message);
                        SessionState.setState(chatId, StateEnum.REGISTER_FULL_NAME);
                    }
                    case REGISTER_FULL_NAME -> {
                        UserCreateDtoMap.USER_CREATE_DTO_MAP.get(chatId).setFullName(update.getMessage().getText());
                        SendMessage message = new SendMessage(chatId, "Send me your contact" + " " + Emoji.BOTTOM);
                        message.setReplyMarkup(ReplyKeyboard.contactMenu());
                        bot.executeMessage(message);
                        SessionState.setState(chatId, StateEnum.REGISTER_PHONE);
                    }
                    case REGISTER_LANGUAGE -> {
                        if (update.getMessage().getText().equals("Uzbek" + " " + Emoji.UZ) || update.getMessage().getText().equals("Russian" + " " + Emoji.RU) || update.getMessage().getText().equals("English" + " " + Emoji.EN)) {
                            UserCreateDtoMap.USER_CREATE_DTO_MAP.get(chatId).setLanguage(update.getMessage().getText().substring(0, 2).toUpperCase());
                            SendMessage message = new SendMessage(chatId, "Choose your position" + " " + Emoji.BOTTOM);
                            message.setReplyMarkup(ReplyKeyboard.positionMenu());
                            bot.executeMessage(message);
                            SessionState.setState(chatId, StateEnum.REGISTER_POSITION);
                        } else {
                            DeleteMessage deleteMessage = new DeleteMessage(chatId, update.getMessage().getMessageId());
                            SendMessage message = new SendMessage(chatId, "Wrong menu" + " " + Emoji.UPSET);
                            bot.executeMessage(deleteMessage);
                            bot.executeMessage(message);
                        }
                    }
                    case REGISTER_POSITION -> {
                        UserCreateDtoMap.USER_CREATE_DTO_MAP.get(chatId).setPosition(update.getMessage().getText().toUpperCase());
                        SendMessage message = new SendMessage(chatId, "Choose your department" + " " + Emoji.BOTTOM);
                        message.setReplyMarkup(ReplyKeyboard.departmentMenu());
                        bot.executeMessage(message);
                        SessionState.setState(chatId, StateEnum.REGISTER_DEPARTMENT);
                    }
                    case REGISTER_DEPARTMENT -> {
                        DepartmentMap.DEPARTMENT_MAP.put(chatId, update.getMessage().getText());
                        ReplyKeyboardRemove remove = new ReplyKeyboardRemove(true);
                        SendMessage message = new SendMessage(chatId, "We have sent your information to your department head, please wait for confirmation." + " " + Emoji.SMILE);
                        message.setReplyMarkup(remove);
                        bot.executeMessage(message);
                        SessionState.setState(chatId, StateEnum.ANONYMOUS);
                        service.register(UserCreateDtoMap.getDto(chatId));

                        DepartmentService departmentService = new DepartmentService(new DepartmentRepository());
                        DepartmentGetDto dto = departmentService.getByName(DepartmentMap.DEPARTMENT_MAP.get(chatId));
                        UserGetDto userGetDto = userService.get(dto.getUserId());

                        UserCreateDto createDto = UserCreateDtoMap.getDto(chatId);
                        SendMessage adminMessage = new SendMessage(userGetDto.getChatId().toString(), "Full name = " + createDto.getFullName() + "\nPhone = " + createDto.getPhone() + "\nPosition = " + createDto.getPosition());
                        adminMessage.setReplyMarkup(InlineKeyboard.acceptMenu());
                        bot.executeMessage(adminMessage);
                    }
                }
            } else if (update.getMessage().hasContact()) {
                if (SessionState.STATE_MAP.get(chatId) == StateEnum.REGISTER_PHONE) {
                    UserCreateDtoMap.USER_CREATE_DTO_MAP.get(chatId).setPhone(update.getMessage().getContact().getPhoneNumber());
                    SendMessage message = new SendMessage(chatId, "Choose your language" + " " + Emoji.BOTTOM);
                    message.setReplyMarkup(ReplyKeyboard.languageMenu());
                    bot.executeMessage(message);
                    SessionState.setState(chatId, StateEnum.REGISTER_LANGUAGE);
                }
            }
        } else if (update.hasCallbackQuery()) {
            String chatId = update.getCallbackQuery().getMessage().getChatId().toString();
            switch (update.getCallbackQuery().getData()) {
                case "YES" -> {
                    DeleteMessage deleteMessage = new DeleteMessage(chatId, update.getCallbackQuery().getMessage().getMessageId());
                    SendMessage message = new SendMessage(chatId, "Thanks Admin, Accepted this staff" + " " + Emoji.ACCEPT);
                    message.setReplyMarkup(InlineKeyboard.deleteMessageMenu());
                    bot.executeMessage(deleteMessage);
                    bot.executeMessage(message);

                    String text = update.getCallbackQuery().getMessage().getText();
                    int begin = text.indexOf("Phone = ");
                    int end = text.indexOf("Position =");
                    String phone = text.substring(begin + 8, end - 1);
                    UserGetDto dto1 = userService.getByPhoneNumber(phone);
                    UserGetDto dto2 = userService.getByChatId(Integer.parseInt(chatId));

                    UserAcceptDepartmentDto acceptDepartmentDto = new UserAcceptDepartmentDto(dto1.getId(), dto2.getDepartmentId());
                    userService.acceptDepartment(acceptDepartmentDto);

                    SendMessage message1 = new SendMessage(dto1.getChatId().toString(), "Thanks, your department head did accept you" + " " + Emoji.SMILE);
                    message1.setReplyMarkup(InlineKeyboard.deleteMessageMenu());
                    bot.executeMessage(message1);
                }
                case "NO" -> {
                    DeleteMessage deleteMessage = new DeleteMessage(chatId, update.getCallbackQuery().getMessage().getMessageId());
                    SendMessage message = new SendMessage(chatId, "Thanks Admin, Not accepted this staff" + " " + Emoji.ACCEPT);
                    message.setReplyMarkup(InlineKeyboard.deleteMessageMenu());
                    bot.executeMessage(deleteMessage);
                    bot.executeMessage(message);

                    String text = update.getCallbackQuery().getMessage().getText();
                    int begin = text.indexOf("Phone = ");
                    int end = text.indexOf("Position =");
                    String phone = text.substring(begin + 8, end - 1);
                    UserGetDto dto = userService.getByPhoneNumber(phone);

                    SendMessage message1 = new SendMessage(dto.getChatId().toString(), "Sorry your department head didn't accept you" + " " + Emoji.UPSET);
                    message1.setReplyMarkup(InlineKeyboard.deleteMessageMenu());
                    bot.executeMessage(message1);
                }
                case "DELETE_MESSAGE" -> {
                    DeleteMessage deleteMessage = new DeleteMessage(chatId, update.getCallbackQuery().getMessage().getMessageId());
                    bot.executeMessage(deleteMessage);
                }
            }
        }
    }
}
