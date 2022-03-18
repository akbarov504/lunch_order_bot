package uz.jl.lunchorderbot.configuration.map;

import lombok.Getter;

import java.util.HashMap;

@Getter
public class SessionMap {
    public static final HashMap<String, String> SESSION_MAP = new HashMap<>();

    public static String getToken(String chatId) {
        return SESSION_MAP.get(chatId);
    }

    public static void setToken(String chatId, String token) {
        SESSION_MAP.replace(chatId, token);
    }
}
