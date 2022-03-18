package uz.jl.lunchorderbot.enums.token;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TokenEnum {
    ACCESS_TOKEN("Access token"),
    REFRESH_TOKEN("Refresh token");

    private final String value;
}
