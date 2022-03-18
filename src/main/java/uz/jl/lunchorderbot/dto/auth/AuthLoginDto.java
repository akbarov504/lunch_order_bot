package uz.jl.lunchorderbot.dto.auth;

import lombok.*;
import uz.jl.lunchorderbot.dto.BaseDto;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuthLoginDto implements BaseDto {
    private String username;
    private String password;
}
