package uz.jl.lunchorderbot.configuration.map;

import lombok.Getter;
import uz.jl.lunchorderbot.dto.auth.AuthLoginDto;

import java.util.HashMap;

@Getter
public class LoginDtoMap {
    public static final HashMap<String, AuthLoginDto> LOGIN_DTO_MAP = new HashMap<>();

    public static AuthLoginDto getDto(String chatId) {
        return LOGIN_DTO_MAP.get(chatId);
    }

    public static void setDto(String chatId, AuthLoginDto dto) {
        LOGIN_DTO_MAP.replace(chatId, dto);
    }
}
