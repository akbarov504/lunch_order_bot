package uz.jl.lunchorderbot.dto.meal;

import lombok.*;
import uz.jl.lunchorderbot.dto.GenericDto;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MealUpdateDto extends GenericDto {
    private String name;

    private String image;

    private String type;
}
