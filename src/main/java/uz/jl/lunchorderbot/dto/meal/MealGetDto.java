package uz.jl.lunchorderbot.dto.meal;

import lombok.*;
import uz.jl.lunchorderbot.dto.GenericDto;

import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MealGetDto extends GenericDto {
    private String name;

    private UUID code;

    private String image;

    private String type;
}
