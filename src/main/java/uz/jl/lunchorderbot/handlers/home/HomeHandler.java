package uz.jl.lunchorderbot.handlers.home;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.jl.lunchorderbot.configuration.bot.LunchOrderBot;
import uz.jl.lunchorderbot.configuration.state.SessionState;
import uz.jl.lunchorderbot.dto.complaint.ComplaintCreateDto;
import uz.jl.lunchorderbot.dto.complaint.ComplaintGetDto;
import uz.jl.lunchorderbot.dto.meal.MealGetDto;
import uz.jl.lunchorderbot.dto.menu.MenuGetDto;
import uz.jl.lunchorderbot.dto.order.OrderCreateDto;
import uz.jl.lunchorderbot.dto.order.OrderGetDto;
import uz.jl.lunchorderbot.dto.user.UserGetDto;
import uz.jl.lunchorderbot.enums.state.StateEnum;
import uz.jl.lunchorderbot.handlers.BaseHandler;
import uz.jl.lunchorderbot.keyboard.InlineKeyboard;
import uz.jl.lunchorderbot.keyboard.ReplyKeyboard;
import uz.jl.lunchorderbot.repository.complaint.ComplaintRepository;
import uz.jl.lunchorderbot.repository.meal.MealRepository;
import uz.jl.lunchorderbot.repository.menu.MenuRepository;
import uz.jl.lunchorderbot.repository.order.OrderRepository;
import uz.jl.lunchorderbot.repository.user.UserRepository;
import uz.jl.lunchorderbot.service.complaint.ComplaintService;
import uz.jl.lunchorderbot.service.meal.MealService;
import uz.jl.lunchorderbot.service.menu.MenuService;
import uz.jl.lunchorderbot.service.order.OrderService;
import uz.jl.lunchorderbot.service.user.UserService;
import uz.jl.lunchorderbot.util.Emoji;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static uz.jl.lunchorderbot.util.Emoji.*;

@Component
public class HomeHandler implements BaseHandler {
    @Override
    public void handler(Update update, LunchOrderBot bot) throws TelegramApiException {
        UserService userService = new UserService(new UserRepository());
        MealService mealService = new MealService(new MealRepository());
        MenuService menuService = new MenuService(new MenuRepository());
        OrderService orderService = new OrderService(new OrderRepository());
        ComplaintService complaintService = new ComplaintService(new ComplaintRepository());

        if (update.hasMessage()) {
            String chatId = update.getMessage().getChatId().toString();
            switch (SessionState.STATE_MAP.get(chatId)) {
                case AUTH_HOME -> {
                    switch (update.getMessage().getText()) {
                        case "Meals" + " " + MEAL -> {
                            List<MealGetDto> list = mealService.list(chatId);
                            for (MealGetDto dto : list) {
                                SendPhoto photo = new SendPhoto();
                                photo.setChatId(chatId);
                                photo.setPhoto(new InputFile(dto.getImage()));
                                photo.setCaption("Meal title = " + " " + dto.getName() + "\nMeal type = " + dto.getType());
                                bot.executePhone(photo);
                            }
                        }
                        case "Menu" + " " + MENU -> {
                            MenuGetDto dto = menuService.getToday(chatId);
                            List<InputMedia> list = new ArrayList<>();
                            StringBuilder text = new StringBuilder();

                            int counter = 1;
                            for (Long meal : dto.getMeals()) {
                                MealGetDto getDto = mealService.get(meal, chatId);
                                InputMediaPhoto photo = new InputMediaPhoto();
                                photo.setMedia(getDto.getImage());
                                photo.setCaption(getDto.getType() + " - " + getDto.getName());
                                list.add(photo);
                                text.append(counter).append(". ").append(getDto.getType()).append(" - ").append(getDto.getName()).append("\n");
                                counter++;
                            }

                            SendMediaGroup group = new SendMediaGroup();
                            group.setChatId(chatId);
                            group.setMedias(list);

                            SendMessage message = new SendMessage(chatId, text.toString());
                            message.setReplyMarkup(InlineKeyboard.orderingMenu());
                            bot.execute(group);
                            bot.executeMessage(message);
                        }
                        case "Order history" + " " + ORDER -> {
                            UserGetDto getDto = userService.getByChatId(Integer.parseInt(chatId));
                            List<OrderGetDto> list = orderService.history(getDto.getId(), chatId);
                            for (OrderGetDto dto : list) {
                                SendPhoto photo = new SendPhoto();
                                photo.setChatId(chatId);
                                MealGetDto mealGetDto = mealService.get(dto.getMealId(), chatId);
                                photo.setPhoto(new InputFile(mealGetDto.getImage()));
                                photo.setCaption("Order information = " + dto.getId() + "\n\nReceive = " + dto.getReceive() + "\n\nMeal information\n\nName = " + mealGetDto.getName() + "\nType = " + mealGetDto.getType());
                                photo.setReplyMarkup(InlineKeyboard.deleteOrderMenu());
                                bot.executePhone(photo);
                            }
                        }
                        case "Complaints" + " " + COMPLAINT -> {
                            SendMessage message = new SendMessage(chatId, "Choose the one menu" + " " + Emoji.BOTTOM);
                            message.setReplyMarkup(ReplyKeyboard.complaintListMenu());
                            bot.executeMessage(message);
                            SessionState.setState(chatId, StateEnum.COMPLAINT_LIST);
                        }
                        case "Logout" + " " + LOGOUT -> {
                            SendMessage message = new SendMessage(chatId, "We say goodbye to you" + " " + Emoji.UPSET);
                            message.setReplyMarkup(ReplyKeyboard.mainMenu());
                            bot.executeMessage(message);
                            SessionState.setState(chatId, StateEnum.CHOOSE_MAIN_MENU);
                        }
                    }
                }
                case ORDERING -> {
                    String mealName = update.getMessage().getText();

                    MealGetDto dto = mealService.getByName(mealName, chatId);
                    UserGetDto getDto = userService.getByChatId(Integer.parseInt(chatId));

                    OrderCreateDto createDto = new OrderCreateDto(LocalDateTime.now(), getDto.getId(), dto.getId(), getDto.getId());
                    orderService.create(createDto, chatId);

                    SendMessage message = new SendMessage(chatId, "Successfully ordered" + " " + ACCEPT);
                    message.setReplyMarkup(ReplyKeyboard.menuMenu());
                    bot.executeMessage(message);
                    SessionState.setState(chatId, StateEnum.AUTH_HOME);
                }
                case COMPLAINT_LIST -> {
                    switch (update.getMessage().getText()) {
                        case "My complaints" + " " + MENU -> {
                            UserGetDto dto = userService.getByChatId(Integer.parseInt(chatId));
                            List<ComplaintGetDto> list = complaintService.list(dto.getId(), chatId);
                            for (ComplaintGetDto getDto : list) {
                                SendMessage message = new SendMessage(chatId, getDto.getId() + ". Message" + " " + BOTTOM + "\n" + getDto.getMessage());
                                message.setReplyMarkup(InlineKeyboard.deleteComplaintMenu());
                                bot.executeMessage(message);
                            }
                        }
                        case "New complaint" + " " + WRITE -> {
                            ReplyKeyboardRemove remove = new ReplyKeyboardRemove(true);
                            SendMessage message = new SendMessage(chatId, "Please write your complaint" + " " + WRITE);
                            message.setReplyMarkup(remove);
                            bot.executeMessage(message);
                            SessionState.setState(chatId, StateEnum.COMPLAINT_WRITE);
                        }
                        case "Back" + " " + BACK -> {
                            SendMessage message = new SendMessage(chatId, "Back" + " " + SMILE);
                            message.setReplyMarkup(ReplyKeyboard.menuMenu());
                            bot.executeMessage(message);
                            SessionState.setState(chatId, StateEnum.AUTH_HOME);
                        }
                    }
                }
                case COMPLAINT_WRITE -> {
                    String text = update.getMessage().getText();
                    UserGetDto getDto = userService.getByChatId(Integer.parseInt(chatId));

                    ComplaintCreateDto dto = new ComplaintCreateDto(text, getDto.getId(), getDto.getId());
                    complaintService.create(dto, chatId);

                    SendMessage message = new SendMessage(chatId, "Successfully write your complaint" + " " + ACCEPT);
                    message.setReplyMarkup(ReplyKeyboard.complaintListMenu());
                    bot.executeMessage(message);
                    SessionState.setState(chatId, StateEnum.COMPLAINT_LIST);
                }
            }
        } else if (update.hasCallbackQuery()) {
            String chatId = update.getCallbackQuery().getMessage().getChatId().toString();
            switch (update.getCallbackQuery().getData()) {
                case "DELETE_ORDER" -> {
                    String text = update.getCallbackQuery().getMessage().getCaption();
                    int begin = text.indexOf("Order information = ") + 20;
                    int end = text.indexOf("Receive = ") - 2;

                    DeleteMessage deleteMessage = new DeleteMessage(chatId, update.getCallbackQuery().getMessage().getMessageId());
                    orderService.delete(Long.parseLong(text.substring(begin, end)), chatId);
                    bot.executeMessage(deleteMessage);
                }
                case "ORDERING" -> {
                    ReplyKeyboardRemove remove = new ReplyKeyboardRemove(true);
                    SendMessage message = new SendMessage(chatId, "Enter the name of the food" + " " + WRITE);
                    message.setReplyMarkup(remove);
                    bot.executeMessage(message);
                    SessionState.setState(chatId, StateEnum.ORDERING);
                }
                case "DELETE_COMPLAINT" -> {
                    String text = update.getCallbackQuery().getMessage().getText();
                    int begin = 0;
                    int end = text.indexOf(".");

                    DeleteMessage deleteMessage = new DeleteMessage(chatId, update.getCallbackQuery().getMessage().getMessageId());
                    complaintService.delete(Long.parseLong(text.substring(begin, end)), chatId);
                    bot.executeMessage(deleteMessage);
                }
            }
        }
    }
}
