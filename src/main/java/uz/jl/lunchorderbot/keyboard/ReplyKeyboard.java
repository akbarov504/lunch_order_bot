package uz.jl.lunchorderbot.keyboard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import uz.jl.lunchorderbot.dto.department.DepartmentGetDto;
import uz.jl.lunchorderbot.repository.department.DepartmentRepository;
import uz.jl.lunchorderbot.service.department.DepartmentService;
import uz.jl.lunchorderbot.util.Emoji;

import java.util.ArrayList;
import java.util.List;

import static uz.jl.lunchorderbot.util.Emoji.*;

public class ReplyKeyboard {
    private static final DepartmentService DEPARTMENT_SERVICE = new DepartmentService(new DepartmentRepository());
    private static final ReplyKeyboardMarkup REPLY_KEYBOARD_MARKUP = new ReplyKeyboardMarkup();

    public static ReplyKeyboardMarkup mainMenu() {
        REPLY_KEYBOARD_MARKUP.setResizeKeyboard(true);
        KeyboardRow row = new KeyboardRow();
        KeyboardButton login = new KeyboardButton("Login" + " " + Emoji.LOGIN);
        KeyboardButton register = new KeyboardButton("Register" + " " + Emoji.REGISTER);
        row.add(login);
        row.add(register);
        REPLY_KEYBOARD_MARKUP.setKeyboard(List.of(row));
        return REPLY_KEYBOARD_MARKUP;
    }

    public static ReplyKeyboardMarkup menuMenu() {
        REPLY_KEYBOARD_MARKUP.setResizeKeyboard(true);
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardRow row3 = new KeyboardRow();

        KeyboardButton meal = new KeyboardButton("Meals" + " " + MEAL);
        KeyboardButton menu = new KeyboardButton("Menu" + " " + MENU);
        KeyboardButton orderHistory = new KeyboardButton("Order history" + " " + ORDER);
        KeyboardButton complaint = new KeyboardButton("Complaints" + " " + COMPLAINT);
        KeyboardButton logout = new KeyboardButton("Logout" + " " + LOGOUT);

        row1.add(meal);
        row1.add(menu);

        row2.add(orderHistory);
        row2.add(complaint);

        row3.add(logout);
        REPLY_KEYBOARD_MARKUP.setKeyboard(List.of(row1, row2, row3));
        return REPLY_KEYBOARD_MARKUP;
    }

    public static ReplyKeyboardMarkup contactMenu() {
        REPLY_KEYBOARD_MARKUP.setResizeKeyboard(true);
        KeyboardRow row = new KeyboardRow();
        KeyboardButton contact = new KeyboardButton("Contact" + " " + Emoji.CONTACT);
        contact.setRequestContact(true);
        row.add(contact);
        REPLY_KEYBOARD_MARKUP.setKeyboard(List.of(row));
        return REPLY_KEYBOARD_MARKUP;
    }

    public static ReplyKeyboardMarkup languageMenu() {
        REPLY_KEYBOARD_MARKUP.setResizeKeyboard(true);
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardButton uz = new KeyboardButton("Uzbek" + " " + Emoji.UZ);
        KeyboardButton ru = new KeyboardButton("Russian" + " " + Emoji.RU);
        KeyboardButton en = new KeyboardButton("English" + " " + Emoji.EN);
        row1.add(uz);
        row1.add(ru);
        row2.add(en);
        REPLY_KEYBOARD_MARKUP.setKeyboard(List.of(row1, row2));
        return REPLY_KEYBOARD_MARKUP;
    }

    public static ReplyKeyboardMarkup positionMenu() {
        REPLY_KEYBOARD_MARKUP.setResizeKeyboard(true);
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardButton admin = new KeyboardButton("Admin" + " " + Emoji.RED);
        KeyboardButton manager = new KeyboardButton("Manager" + " " + Emoji.YELLOW);
        KeyboardButton mentor = new KeyboardButton("Mentor" + " " + Emoji.BLUE);
        KeyboardButton employee = new KeyboardButton("Employee" + " " + Emoji.GREEN);
        row1.add(admin);
        row1.add(manager);
        row2.add(mentor);
        row2.add(employee);
        REPLY_KEYBOARD_MARKUP.setKeyboard(List.of(row1, row2));
        return REPLY_KEYBOARD_MARKUP;
    }

    public static ReplyKeyboardMarkup departmentMenu() {
        REPLY_KEYBOARD_MARKUP.setResizeKeyboard(true);
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        List<KeyboardButton> keyboardButtons = new ArrayList<>();
        List<DepartmentGetDto> list = DEPARTMENT_SERVICE.list();
        int size = list.size();

        if (size % 2 != 0) {
            size += 1;
            keyboardButtons.add(new KeyboardButton("."));
        }

        for (int i = 0; i < size / 2; i++) {
            keyboardRows.add(new KeyboardRow());
        }

        for (DepartmentGetDto dto : list) {
            keyboardButtons.add(new KeyboardButton(dto.getName()));
        }

        int index = 0;
        for (KeyboardRow keyboardRow : keyboardRows) {
            for (int j = 0; j < 2; j++) {
                keyboardRow.add(keyboardButtons.get(index));
                index++;
            }
        }

        REPLY_KEYBOARD_MARKUP.setKeyboard(keyboardRows);
        return REPLY_KEYBOARD_MARKUP;
    }

    public static ReplyKeyboardMarkup complaintListMenu() {
        REPLY_KEYBOARD_MARKUP.setResizeKeyboard(true);
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardButton yesterday = new KeyboardButton("My complaints" + " " + MENU);
        KeyboardButton today = new KeyboardButton("New complaint" + " " + WRITE);
        KeyboardButton back = new KeyboardButton("Back" + " " + BACK);
        row1.add(yesterday);
        row1.add(today);
        row2.add(back);
        REPLY_KEYBOARD_MARKUP.setKeyboard(List.of(row1, row2));
        return REPLY_KEYBOARD_MARKUP;
    }
}
