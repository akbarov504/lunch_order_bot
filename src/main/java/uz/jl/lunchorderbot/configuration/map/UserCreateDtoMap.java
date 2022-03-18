package uz.jl.lunchorderbot.configuration.map;

import lombok.Getter;
import uz.jl.lunchorderbot.dto.user.UserCreateDto;

import java.util.HashMap;

@Getter
public class UserCreateDtoMap {
    public static final HashMap<String, UserCreateDto> USER_CREATE_DTO_MAP = new HashMap<>();

    public static UserCreateDto getDto(String chatId) {
        return USER_CREATE_DTO_MAP.get(chatId);
    }

    public static void setDto(String chatId, UserCreateDto dto) {
        USER_CREATE_DTO_MAP.replace(chatId, dto);
    }
}
