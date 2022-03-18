package uz.jl.lunchorderbot.dto.token;

import lombok.*;
import uz.jl.lunchorderbot.dto.GenericDto;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TokenUpdateDto extends GenericDto {
    private String token;
}
