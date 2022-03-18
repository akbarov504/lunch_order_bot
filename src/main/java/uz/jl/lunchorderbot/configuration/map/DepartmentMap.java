package uz.jl.lunchorderbot.configuration.map;

import lombok.Getter;

import java.util.HashMap;

@Getter
public class DepartmentMap {
    public static final HashMap<String, String> DEPARTMENT_MAP = new HashMap<>();

    public static String getName(String chatId) {
        return DEPARTMENT_MAP.get(chatId);
    }

    public static void setName(String chatId, String name) {
        DEPARTMENT_MAP.replace(chatId, name);
    }
}
