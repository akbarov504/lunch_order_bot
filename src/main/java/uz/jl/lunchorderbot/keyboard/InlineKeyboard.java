package uz.jl.lunchorderbot.keyboard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import uz.jl.lunchorderbot.util.Emoji;

import java.util.ArrayList;
import java.util.List;

public class InlineKeyboard {
    private static final InlineKeyboardMarkup INLINE_KEYBOARD_MARKUP = new InlineKeyboardMarkup();

    public static InlineKeyboardMarkup acceptMenu() {
        InlineKeyboardButton yes = new InlineKeyboardButton("Yes" + " " + Emoji.YES);
        yes.setCallbackData("YES");

        InlineKeyboardButton no = new InlineKeyboardButton("No" + " " + Emoji.NO);
        no.setCallbackData("NO");

        List<InlineKeyboardButton> row = new ArrayList<>();
        row.add(yes);
        row.add(no);

        INLINE_KEYBOARD_MARKUP.setKeyboard(List.of(row));
        return INLINE_KEYBOARD_MARKUP;
    }

    public static InlineKeyboardMarkup deleteMessageMenu() {
        InlineKeyboardButton delete = new InlineKeyboardButton("Delete" + " " + Emoji.NO);
        delete.setCallbackData("DELETE_MESSAGE");

        List<InlineKeyboardButton> row = new ArrayList<>();
        row.add(delete);

        INLINE_KEYBOARD_MARKUP.setKeyboard(List.of(row));
        return INLINE_KEYBOARD_MARKUP;
    }

    public static InlineKeyboardMarkup deleteOrderMenu() {
        InlineKeyboardButton delete = new InlineKeyboardButton("Delete" + " " + Emoji.NO);
        delete.setCallbackData("DELETE_ORDER");

        List<InlineKeyboardButton> row = new ArrayList<>();
        row.add(delete);

        INLINE_KEYBOARD_MARKUP.setKeyboard(List.of(row));
        return INLINE_KEYBOARD_MARKUP;
    }

    public static InlineKeyboardMarkup orderingMenu() {
        InlineKeyboardButton delete = new InlineKeyboardButton("Ordering" + " " + Emoji.ORDER);
        delete.setCallbackData("ORDERING");

        List<InlineKeyboardButton> row = new ArrayList<>();
        row.add(delete);

        INLINE_KEYBOARD_MARKUP.setKeyboard(List.of(row));
        return INLINE_KEYBOARD_MARKUP;

    }

    public static InlineKeyboardMarkup deleteComplaintMenu() {
        InlineKeyboardButton delete = new InlineKeyboardButton("Delete" + " " + Emoji.NO);
        delete.setCallbackData("DELETE_COMPLAINT");

        List<InlineKeyboardButton> row = new ArrayList<>();
        row.add(delete);

        INLINE_KEYBOARD_MARKUP.setKeyboard(List.of(row));
        return INLINE_KEYBOARD_MARKUP;
    }
}
