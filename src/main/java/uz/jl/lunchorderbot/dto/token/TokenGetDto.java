package uz.jl.lunchorderbot.dto.token;

import lombok.*;
import uz.jl.lunchorderbot.dto.GenericDto;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TokenGetDto extends GenericDto {
    private String token;
    private String type;
}
