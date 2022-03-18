package uz.jl.lunchorderbot.configuration.state;

import lombok.Getter;
import uz.jl.lunchorderbot.enums.state.StateEnum;

import java.util.HashMap;

@Getter
public class SessionState {
    public static final HashMap<String, StateEnum> STATE_MAP = new HashMap<>();

    public static StateEnum getState(String chatId) {
        return STATE_MAP.get(chatId);
    }

    public static void setState(String chatId, StateEnum state) {
        STATE_MAP.replace(chatId, state);
    }
}
